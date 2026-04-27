package s3526603.waterreminderapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import s3526603.waterreminderapp.screens.HomeScreen
import s3526603.waterreminderapp.screens.LoginScreen
import s3526603.waterreminderapp.screens.ReminderScreen
import s3526603.waterreminderapp.screens.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NotificationHelper.createNotificationChannel(this)

        setContent {
            MaterialTheme {
                val context = this

                var showSplash by remember { mutableStateOf(true) }
                var showHome by remember { mutableStateOf(false) }
                var showSettings by remember { mutableStateOf(false) }

                var goal by remember { mutableIntStateOf(DataManager.getGoal(context)) }
                var intake by remember { mutableIntStateOf(DataManager.getIntake(context)) }

                LaunchedEffect(Unit) {
                    delay(2000)
                    showSplash = false
                }

                when {
                    showSplash -> {
                        SplashScreen()
                    }

                    !showHome -> {
                        LoginScreen(
                            onLoginClick = { email, password ->
                                FirebaseManager.auth
                                    .signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(
                                                context,
                                                "Sign in successful",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            val userId = FirebaseManager.auth.currentUser?.uid

                                            if (userId != null) {
                                                FirebaseManager.db.collection("users")
                                                    .document(userId)
                                                    .get()
                                                    .addOnSuccessListener { document ->
                                                        if (document.exists()) {
                                                            goal = document.getLong("goal")?.toInt() ?: goal
                                                            intake = document.getLong("intake")?.toInt() ?: intake

                                                            DataManager.saveGoal(context, goal)
                                                            DataManager.saveIntake(context, intake)

                                                            val interval =
                                                                document.getString("interval") ?: "1 hour"
                                                            val notificationsEnabled =
                                                                document.getBoolean("notificationsEnabled") ?: true

                                                            DataManager.saveInterval(context, interval)
                                                            DataManager.saveNotificationsEnabled(
                                                                context,
                                                                notificationsEnabled
                                                            )
                                                        }
                                                        showHome = true
                                                    }
                                                    .addOnFailureListener { e ->
                                                        Toast.makeText(
                                                            context,
                                                            "Failed to load cloud data: ${e.message}",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        showHome = true
                                                    }
                                            } else {
                                                showHome = true
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Sign in failed: ${task.exception?.message}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                            },
                            onCreateAccountClick = { email, password ->
                                FirebaseManager.auth
                                    .createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(
                                                context,
                                                "Account created successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            val userId = FirebaseManager.auth.currentUser?.uid

                                            if (userId != null) {
                                                val userData = hashMapOf(
                                                    "goal" to goal,
                                                    "intake" to intake,
                                                    "interval" to DataManager.getInterval(context),
                                                    "notificationsEnabled" to DataManager.getNotificationsEnabled(context)
                                                )

                                                FirebaseManager.db.collection("users")
                                                    .document(userId)
                                                    .set(userData)
                                                    .addOnSuccessListener {
                                                        showHome = true
                                                    }
                                                    .addOnFailureListener { e ->
                                                        Toast.makeText(
                                                            context,
                                                            "Firestore save failed: ${e.message}",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }
                                            } else {
                                                showHome = true
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Create account failed: ${task.exception?.message}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                            }
                        )
                    }

                    showSettings -> {
                        ReminderScreen(
                            currentGoal = goal,
                            currentInterval = DataManager.getInterval(context),
                            currentNotificationsEnabled = DataManager.getNotificationsEnabled(context),
                            onSaveClick = { newGoal, newInterval, notificationsEnabled ->
                                goal = newGoal
                                DataManager.saveGoal(context, newGoal)
                                DataManager.saveInterval(context, newInterval)
                                DataManager.saveNotificationsEnabled(context, notificationsEnabled)

                                val intervalMinutes = when {
                                    newInterval.contains("15") -> 15L
                                    newInterval.contains("30") -> 30L
                                    newInterval.contains("60") -> 60L
                                    newInterval.contains("1 hour", ignoreCase = true) -> 60L
                                    newInterval.contains("120") -> 120L
                                    newInterval.contains("2 hour", ignoreCase = true) -> 120L
                                    newInterval.contains("180") -> 180L
                                    newInterval.contains("3 hour", ignoreCase = true) -> 180L
                                    else -> 60L
                                }

                                if (notificationsEnabled) {
                                    ReminderScheduler.scheduleReminder(context, intervalMinutes)
                                    Toast.makeText(
                                        context,
                                        "Automatic reminder scheduled every $intervalMinutes minute(s)",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    ReminderScheduler.cancelReminder(context)
                                    Toast.makeText(
                                        context,
                                        "Automatic reminders turned off",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                val userId = FirebaseManager.auth.currentUser?.uid
                                if (userId != null) {
                                    val userData = hashMapOf(
                                        "goal" to newGoal,
                                        "intake" to intake,
                                        "interval" to newInterval,
                                        "notificationsEnabled" to notificationsEnabled
                                    )

                                    FirebaseManager.db.collection("users")
                                        .document(userId)
                                        .set(userData)
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                context,
                                                "Settings saved",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(
                                                context,
                                                "Firestore save failed: ${e.message}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                }

                                showSettings = false
                            },
                            onTestNotificationClick = {
                                NotificationHelper.showNotification(context)
                            },
                            onBackClick = {
                                showSettings = false
                            }
                        )
                    }

                    else -> {
                        HomeScreen(
                            goal = goal,
                            intake = intake,
                            onAdd100 = {
                                intake += 100
                                DataManager.saveIntake(context, intake)

                                val userId = FirebaseManager.auth.currentUser?.uid
                                if (userId != null) {
                                    FirebaseManager.db.collection("users")
                                        .document(userId)
                                        .update("intake", intake)
                                }
                            },
                            onAdd250 = {
                                intake += 250
                                DataManager.saveIntake(context, intake)

                                val userId = FirebaseManager.auth.currentUser?.uid
                                if (userId != null) {
                                    FirebaseManager.db.collection("users")
                                        .document(userId)
                                        .update("intake", intake)
                                }
                            },
                            onAdd500 = {
                                intake += 500
                                DataManager.saveIntake(context, intake)

                                val userId = FirebaseManager.auth.currentUser?.uid
                                if (userId != null) {
                                    FirebaseManager.db.collection("users")
                                        .document(userId)
                                        .update("intake", intake)
                                }
                            },
                            onReset = {
                                intake = 0
                                DataManager.saveIntake(context, intake)

                                val userId = FirebaseManager.auth.currentUser?.uid
                                if (userId != null) {
                                    FirebaseManager.db.collection("users")
                                        .document(userId)
                                        .update("intake", intake)
                                }
                            },
                            onSettingsClick = { showSettings = true },
                            onLogoutClick = {
                                FirebaseManager.auth.signOut()
                                showHome = false
                                showSettings = false
                                Toast.makeText(
                                    context,
                                    "Logged out successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                }
            }
        }
    }
}