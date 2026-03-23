package s3526603.waterreminderapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReminderScreen(
    currentGoal: Int,
    onSaveClick: (Int) -> Unit
) {
    var goal by remember { mutableStateOf(currentGoal.toString()) }
    var reminderEnabled by remember { mutableStateOf(true) }
    var interval by remember { mutableStateOf("2 hours") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Reminder Settings")

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = goal,
            onValueChange = { goal = it },
            label = { Text("Daily Goal (ml)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = interval,
            onValueChange = { interval = it },
            label = { Text("Reminder Interval") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Enable Notifications")

        Switch(
            checked = reminderEnabled,
            onCheckedChange = { reminderEnabled = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                onSaveClick(goal.toIntOrNull() ?: 2000)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Settings")
        }
    }
}