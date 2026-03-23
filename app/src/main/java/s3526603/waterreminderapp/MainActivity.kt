package s3526603.waterreminderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import s3526603.waterreminderapp.screens.HomeScreen
import s3526603.waterreminderapp.screens.LoginScreen
import s3526603.waterreminderapp.screens.ReminderScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var showHome by remember { mutableStateOf(false) }
                var showSettings by remember { mutableStateOf(false) }
                var goal by remember { mutableIntStateOf(2000) }

                if (!showHome) {
                    LoginScreen(
                        onLoginClick = { showHome = true }
                    )
                } else if (showSettings) {
                    ReminderScreen(
                        currentGoal = goal,
                        onSaveClick = { newGoal ->
                            goal = newGoal
                            showSettings = false
                        }
                    )
                } else {
                    HomeScreen(
                        goal = goal,
                        onSettingsClick = { showSettings = true }
                    )
                }
            }
        }
    }
}