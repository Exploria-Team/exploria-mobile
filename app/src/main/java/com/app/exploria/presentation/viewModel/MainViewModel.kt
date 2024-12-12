package com.app.exploria.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.exploria.data.models.userData.UserModel
import com.app.exploria.data.remote.api.ApiConfig
import com.app.exploria.data.remote.response.LoginUser
import com.app.exploria.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val apiConfig: ApiConfig
) : ViewModel() {
    private val _user = MutableStateFlow<LoginUser?>(null)
    val user: StateFlow<LoginUser?> get() = _user

    private val _userModel =
        MutableStateFlow<UserModel?>(null)
    val userModel: StateFlow<UserModel?> get() = _userModel

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> get() = _isRegistered

    fun loadUser() {
        viewModelScope.launch {
            userRepository.getUserSession().collect { userModel ->
                _userModel.value = userModel
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

    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = userRepository.login(email, password)

            result.onSuccess { loginResponse ->
                val userModel = UserModel(
                    id = loginResponse.user.id,
                    name = loginResponse.user.name,
                    email = loginResponse.user.email,
                    token = loginResponse.token,
                    profilePictureUrl = loginResponse.user.profilePictureUrl ?: "",
                    isLogin = true
                )
                _user.value = loginResponse.user
                _userModel.value = userModel
                clearErrorMessage()
                saveSession(userModel)
            }.onFailure {
                _errorMessage.value = parseErrorMessage(it)
            }
            _isLoading.value = false
        }
    }


    fun register(name: String, email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = userRepository.register(name, email, password)

            result.onSuccess {
                _isRegistered.value = true
                clearErrorMessage()
            }.onFailure {
                _errorMessage.value = parseErrorMessage(it)
            }
            _isLoading.value = false
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    private fun parseErrorMessage(exception: Throwable): String {
        return if (exception is HttpException) {
            val errorJson = exception.response()?.errorBody()?.string()
            val errorResponse = errorJson?.let { apiConfig.parseError(it) }
            errorResponse?.message ?: "Unknown error occurred"
        } else {
            exception.message ?: "An error occurred"
        }
    }

    fun setErrorMessage(message: String?) {
        _errorMessage.value = message
    }
}