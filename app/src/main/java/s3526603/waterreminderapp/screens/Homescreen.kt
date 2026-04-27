package s3526603.waterreminderapp.screens

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    goal: Int,
    intake: Int,
    onAdd100: () -> Unit,
    onAdd250: () -> Unit,
    onAdd500: () -> Unit,
    onReset: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val progress = if (goal > 0) intake.toFloat() / goal.toFloat() else 0f
    val safeProgress = progress.coerceIn(0f, 1f)
    val percentage = (safeProgress * 100).toInt()

    val statusMessage = when {
        percentage >= 100 -> "👏 Amazing! You completed your goal today!"
        percentage >= 75 -> "💪 Almost there! Keep sipping!"
        percentage >= 40 -> "✨ Great progress! Stay hydrated!"
        else -> "🌊 A good start for the day!"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2FAFF))
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "💧 Water Reminder",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF0D47A1),
                    fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "✨ Stay healthy, fresh, and hydrated every day",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF546E7A),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(22.dp)
            ) {
                Text(
                    text = "🎯 Today's Progress",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF01579B),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "$intake / $goal ml",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF263238)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🥛",
                        fontSize = 26.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    LinearProgressIndicator(
                        progress = { safeProgress },
                        modifier = Modifier
                            .weight(1f)
                            .height(12.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "💧",
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Start",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF607D8B),
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Goal",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF607D8B),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "💦 $percentage% completed",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF37474F),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = statusMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF0277BD),
                    fontWeight = FontWeight.Bold
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
                    text = "➕ Quick Add",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF01579B),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onAdd100,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF81D4FA)
                        )
                    ) {
                        Text("🥛 +100")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = onAdd250,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4FC3F7)
                        )
                    ) {
                        Text("🥛 +250")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = onAdd500,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF29B6F6)
                        )
                    ) {
                        Text("🥛 +500")
                    }
                }
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
                    text = "⚙️ More Actions",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF01579B)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = onReset,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0288D1)
                    )
                ) {
                    Text(" Reset Today")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onSettingsClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF01579B)
                    )
                ) {
                    Text("⏰ Reminder Settings")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onLogoutClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC62828)
                    )
                ) {
                    Text(" Logout")
                }
            }
        }
    }
}