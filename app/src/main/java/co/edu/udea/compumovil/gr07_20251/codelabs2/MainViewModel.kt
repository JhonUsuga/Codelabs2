package co.edu.udea.compumovil.gr07_20251.codelabs2

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

class MainViewModel : ViewModel() {
    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text

    private val _selectedDate = MutableStateFlow("")
    val selectedDate: StateFlow<String> = _selectedDate

    private val _selectedTime = MutableStateFlow("")
    val selectedTime: StateFlow<String> = _selectedTime

    fun onTextChange(newText: String) {
        _text.value = newText
    }

    fun onDateSelected(date: String) {
        _selectedDate.value = date
    }

    fun onTimeSelected(time: String) {
        _selectedTime.value = time
    }
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun onPasswordChange(new: String) {
        _password.value = new
    }

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    fun onUsernameChange(new: String) {
        _username.value = new
    }

}
