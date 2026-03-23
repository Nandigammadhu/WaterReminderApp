package s3526603.waterreminderapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    goal: Int,
    onSettingsClick: () -> Unit
) {
    var intake by remember { mutableIntStateOf(0) }
    val progress = if (goal > 0) intake.toFloat() / goal.toFloat() else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Today's Intake",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "$intake / $goal ml",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxSize().weight(0f, false)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { intake += 250 }) {
            Text("+250 ml")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { intake += 500 }) {
            Text("+500 ml")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { intake = 0 }) {
            Text("Reset")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onSettingsClick) {
            Text("Reminder Settings")
        }
    }
}