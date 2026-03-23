package s3526603.waterreminderapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {

    var intake by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(text = "Today's Intake")

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "$intake ml")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { intake += 250 }) {
            Text("+250 ml")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { intake += 500 }) {
            Text("+500 ml")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { intake = 0 }) {
            Text("Reset")
        }
    }
}