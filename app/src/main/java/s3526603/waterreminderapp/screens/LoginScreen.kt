package s3526603.waterreminderapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onCreateAccountClick: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4FBFF))
    ) {
        Text(
            text = "💧",
            fontSize = 90.sp,
            color = Color(0x3329B6F6),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 20.dp, y = 40.dp)
        )

        Text(
            text = "💧",
            fontSize = 70.sp,
            color = Color(0x2239C0ED),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-24).dp, y = 120.dp)
        )

        Text(
            text = "💧",
            fontSize = 110.sp,
            color = Color(0x1F4FC3F7),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 10.dp, y = (-60).dp)
        )

        Text(
            text = "💧",
            fontSize = 85.sp,
            color = Color(0x224FC3F7),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-30).dp, y = (-100).dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "💧 Welcome",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sign in to save and restore your hydration data",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF546E7A)
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
                        value = email,
                        onValueChange = {
                            email = it
                            errorMessage = ""
                        },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorMessage = ""
                        },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        visualTransformation = if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = {
                            TextButton(onClick = { passwordVisible = !passwordVisible }) {
                                Text(if (passwordVisible) "Hide" else "Show")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Button(
                        onClick = {
                            if (email.trim().isEmpty() || password.trim().isEmpty()) {
                                errorMessage = "Please enter both email and password"
                            } else if (password.trim().length < 6) {
                                errorMessage = "Password must be at least 6 characters"
                            } else {
                                onLoginClick(email.trim(), password.trim())
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF01579B)
                        )
                    ) {
                        Text("Sign In")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            if (email.trim().isEmpty() || password.trim().isEmpty()) {
                                errorMessage = "Please enter both email and password"
                            } else if (password.trim().length < 6) {
                                errorMessage = "Password must be at least 6 characters"
                            } else {
                                onCreateAccountClick(email.trim(), password.trim())
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4FC3F7)
                        )
                    ) {
                        Text("Create Account")
                    }
                }
            }
        }
    }
}