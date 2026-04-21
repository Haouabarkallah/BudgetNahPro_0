package com.budgetnah.pro.presentation.features.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgetnah.pro.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                val result = repository.login(email, password)
                if (result.isSuccess) {
                    onSuccess()
                } else {
                    error = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun register(email: String, password: String, fullName: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                val result = repository.register(email, password, fullName)
                if (result.isSuccess) {
                    onSuccess()
                } else {
                    error = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}