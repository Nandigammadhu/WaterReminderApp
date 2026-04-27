package s3526603.waterreminderapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReminderScreen(
    currentGoal: Int,
    currentInterval: String,
    currentNotificationsEnabled: Boolean,
    onSaveClick: (Int, String, Boolean) -> Unit,
    onTestNotificationClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var goal by remember { mutableStateOf(currentGoal.toString()) }
    var interval by remember { mutableStateOf(currentInterval) }
    var reminderEnabled by remember { mutableStateOf(currentNotificationsEnabled) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2FAFF))
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }

            Text(
                text = "⏰ Reminder Settings",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF0D47A1)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "✨ Manage your goal and reminder preferences",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF546E7A)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "🎯 Current Summary",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF01579B)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "💧 Daily Goal: $currentGoal ml",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "⏳ Reminder Interval: $currentInterval",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (currentNotificationsEnabled) {
                        "🔔 Notifications: Enabled"
                    } else {
                        "🔕 Notifications: Disabled"
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = "🛠️ Update Preferences",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF01579B)
                )

                Spacer(modifier = Modifier.height(14.dp))

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

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "🔔 Enable Notifications",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Switch(
                        checked = reminderEnabled,
                        onCheckedChange = { reminderEnabled = it }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = onTestNotificationClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4FC3F7)
            )
        ) {
            Text("🔔 Test Reminder Notification")
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
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF01579B)
            )
        ) {
            Text("💾 Save Settings")
        }
    }
}