package s3526603.waterreminderapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReminderScreen() {

    var goal by remember { mutableStateOf("2000") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(text = "Reminder Settings")

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = goal,
            onValueChange = { goal = it },
            label = { Text("Daily Goal (ml)") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {}) {
            Text("Save Settings")
        }
    }
}