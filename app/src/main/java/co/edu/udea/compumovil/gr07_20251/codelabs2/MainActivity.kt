package co.edu.udea.compumovil.gr07_20251.codelabs2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MaterialTheme {
                NavHost(navController = navController, startDestination = "login") {

                    // Pantalla de inicio de sesión
                    composable("login") {
                        AppScreen(viewModel = viewModel, navController = navController)
                    }

                    // Pantalla de bienvenida
                    composable(
                        route = "home/{username}",
                        arguments = listOf(navArgument("username") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username") ?: ""
                        HomeScreen(username = username, navController = navController)
                    }
                }
            }
        }
    }
}

