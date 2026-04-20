package s3526603.waterreminderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
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
                            onLoginClick = { showHome = true }
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
                                showSettings = false
                            },
                            onTestNotificationClick = {
                                NotificationHelper.showNotification(context)
                            }
                        )
                    }

                    else -> {
                        HomeScreen(
                            goal = goal,
                            intake = intake,
                            onAdd250 = {
                                intake += 250
                                DataManager.saveIntake(context, intake)
                            },
                            onAdd500 = {
                                intake += 500
                                DataManager.saveIntake(context, intake)
                            },
                            onReset = {
                                intake = 0
                                DataManager.saveIntake(context, intake)
                            },
                            onSettingsClick = { showSettings = true }
                        )
                    }
                }
            }
        }
    }
}