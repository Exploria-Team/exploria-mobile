package com.app.exploria.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.exploria.data.models.userData.UserModel
import com.app.exploria.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _user = MutableStateFlow<UserModel?>(null)
    val user: StateFlow<UserModel?> get() = _user

    fun loadUser() {
        viewModelScope.launch {
            userRepository.getUserSession().collect { userModel ->
                _user.value = userModel
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            _user.value = null
        }
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveUserSession(user)
        }
    }
}