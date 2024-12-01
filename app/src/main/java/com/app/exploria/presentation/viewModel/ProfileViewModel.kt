package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.app.exploria.data.remote.response.Data
import com.app.exploria.data.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseViewModel() {
    private val _userData = MutableStateFlow<Data?>(null)
    val userData: StateFlow<Data?> get() = _userData

    fun getDataUser(id: String) {
        setLoading(true)
        viewModelScope.launch {
            val result = profileRepository.getUserData(id)

            result.onSuccess { data ->
                _userData.value = data
                clearErrorMessage()
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }

    fun updateDataUser(
        id: String,
        name: String?,
        email: String?,
        profilePictureUrl: String?,
        birthdate: String?
    ) {
        setLoading(true)
        viewModelScope.launch {
            val result =
                profileRepository.updateUserData(id, name, email, profilePictureUrl, birthdate)

            result.onSuccess { updatedData ->
                _userData.value = updatedData
                clearErrorMessage()
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }
}
