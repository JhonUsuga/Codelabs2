package co.edu.udea.compumovil.gr07_20251.codelabs2

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import android.widget.Toast
import androidx.navigation.NavController




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavController
) {

    val password by viewModel.password.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    val username by viewModel.username.collectAsState()

    val text by viewModel.text.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()
    val selectedTime by viewModel.selectedTime.collectAsState()

    val context = LocalContext.current
    val openDatePicker = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Column(modifier = Modifier.padding(24.dp)) {
        TextField(
            value = username,
            onValueChange = viewModel::onUsernameChange,
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        if (username.isEmpty()) {
            Text(
                text = "El nombre de usuario no puede estar vacío",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña")
                }
            }
        )

        val isPasswordValid = password.length >= 6
        if (!isPasswordValid && password.isNotEmpty()) {
            Text(
                text = "La contraseña debe tener al menos 6 caracteres",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { openDatePicker.value = true }) {
            Text("Seleccionar fecha")
        }

        if (selectedDate.isNotEmpty()) {
            Text("Fecha: $selectedDate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val cal = Calendar.getInstance()
            TimePickerDialog(
                context,
                { _, hour: Int, minute: Int ->
                    viewModel.onTimeSelected(String.format("%02d:%02d", hour, minute))
                },
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }) {
            Text("Seleccionar hora")
        }

        if (selectedTime.isNotEmpty()) {
            Text("Hora: $selectedTime")
        }

        if (openDatePicker.value) {
            DatePickerDialog(
                onDismissRequest = { openDatePicker.value = false },
                confirmButton = {
                    TextButton(onClick = {
                        openDatePicker.value = false
                        datePickerState.selectedDateMillis?.let {
                            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            viewModel.onDateSelected(formatter.format(Date(it)))
                        }
                    }) { Text("Aceptar") }
                },
                dismissButton = {
                    TextButton(onClick = { openDatePicker.value = false }) {
                        Text("Cancelar")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (username.isNotEmpty() && password.length >= 6) {
                navController.navigate("home/$username")
            } else {
                Toast.makeText(context, "Por favor completa los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Iniciar sesión")
        }

    }
}
