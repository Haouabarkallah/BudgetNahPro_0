package com.budgetnah.pro.presentation.features.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.budgetnah.pro.presentation.core.components.PrimaryButton

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterSuccess: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Connexion 🔐", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nom complet") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Se connecter",
            onClick = {
                viewModel.login(email, password, onLoginSuccess)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = {
                viewModel.register(email, password, fullName, onRegisterSuccess)
            }
        ) {
            Text("Créer un compte")
        }
    }
}