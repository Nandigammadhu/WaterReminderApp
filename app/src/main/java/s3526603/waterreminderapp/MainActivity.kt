package s3526603.waterreminderapp

import s3526603.waterreminderapp.screens.HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "💧 Water Reminder",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Today's Intake: 0 ml",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = {}) {
                        Text("+250 ml")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {}) {
                        Text("+500 ml")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {}) {
                        Text("Reset")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {}) {
                        Text("Reminder Settings")
                    }
                }
            }
        }
    }
}

