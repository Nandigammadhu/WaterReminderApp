package s3526603.waterreminderapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReminderScreen(
    currentGoal: Int,
    currentInterval: String,
    currentNotificationsEnabled: Boolean,
    onSaveClick: (Int, String, Boolean) -> Unit,
    onTestNotificationClick: () -> Unit
) {
    var goal by remember { mutableStateOf(currentGoal.toString()) }
    var interval by remember { mutableStateOf(currentInterval) }
    var reminderEnabled by remember { mutableStateOf(currentNotificationsEnabled) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAF6FF))
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "⏰ Reminder Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Manage your daily goal and reminder preferences",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                OutlinedTextField(
                    value = goal,
                    onValueChange = { goal = it },
                    label = { Text("Daily Goal (ml)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = interval,
                    onValueChange = { interval = it },
                    label = { Text("Reminder Interval") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Enable Notifications",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Switch(
                    checked = reminderEnabled,
                    onCheckedChange = { reminderEnabled = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onTestNotificationClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4FC3F7)
            )
        ) {
            Text("Test Reminder Notification")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                onSaveClick(
                    goal.toIntOrNull() ?: 2000,
                    interval,
                    reminderEnabled
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF01579B)
            )
        ) {
            Text("Save Settings")
        }
    }
}