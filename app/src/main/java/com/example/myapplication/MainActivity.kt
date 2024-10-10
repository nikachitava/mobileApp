package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginForm(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }


    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text (
            text = "Welcome back",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,

        )
        Spacer(modifier = Modifier.height(60.dp))
        TextField(
            value = email,
            onValueChange = {
                email = it
                emailError = if (emailPattern.matcher(it).matches()) null else "Invalid email address"
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = emailError != null
        )
        if (emailError != null) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = emailError ?: "",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,

            )
        }
        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = PasswordValidation(password)
                            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()

        )
        if (passwordError != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = passwordError ?: "",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "I Don't have an account",

            fontSize = 12.sp,
            color = Color.Black.copy(alpha = 0.6f)
        )


        Button(
            onClick = {
                if (emailError == null && email.isNotEmpty() && password.isNotEmpty()) {
                    // Handle valid form submission
                } else {
                    if (email.isEmpty()) {
                        emailError = "Email field is required"
                    }
                    if (password.isEmpty()) {
                        passwordError = "Password field is required"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = email.isNotEmpty() && password.isNotEmpty()
        ) {
            Text("Submit")
        }
    }
}

fun PasswordValidation(password: String): String? {

    val lengthError = "Password must be at least 8 characters long."
    val upperCaseError = "Password must contain at least one uppercase letter."
    val lowerCaseError = "Password must contain at least one lowercase letter."
    val numberError = "Password must contain at least one number."
    val specialCharError = "Password must contain at least one special character (!@#$%^&*)."


    if (password.length < 8) return lengthError
    if (!password.any { it.isUpperCase() }) return upperCaseError
    if (!password.any { it.isLowerCase() }) return lowerCaseError
    if (!password.any { it.isDigit() }) return numberError
    if (!password.any { it in "!@#$%^&*" }) return specialCharError

    return null
}

@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    MyApplicationTheme {
        LoginForm()
    }
}
