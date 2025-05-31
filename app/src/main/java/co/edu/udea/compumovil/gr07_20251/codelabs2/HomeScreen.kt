package co.edu.udea.compumovil.gr07_20251.codelabs2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(username: String, navController: NavController)  {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenido, $username 👋",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Has iniciado sesión exitosamente.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
        }) {
            Text("Cerrar sesión")
        }
    }
}
