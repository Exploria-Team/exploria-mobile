package com.app.exploria.presentation.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.app.exploria.data.remote.response.Data
import com.app.exploria.data.remote.response.GetPreferenceDataItem
import com.app.exploria.data.remote.response.PreferenceResponse
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

    private val _postPreferenceResult = mutableStateOf<Result<PreferenceResponse>?>(null)
    val postPreferenceResult: State<Result<PreferenceResponse>?> = _postPreferenceResult

    private val _preferencesResult =
        mutableStateOf<Result<List<GetPreferenceDataItem>>>(Result.success(emptyList()))
    val preferencesResult: State<Result<List<GetPreferenceDataItem>>> get() = _preferencesResult

    private val _updateDataUserResult = MutableStateFlow<Result<Data>?>(null)
    val updateDataUserResult: StateFlow<Result<Data>?> get() = _updateDataUserResult

    fun getDataUser(id: Int) {
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
        id: Int,
        name: String?,
        email: String?,
        profilePictureUri: Uri?,
        age: String?
    ) {
        setLoading(true)
        viewModelScope.launch {
            val result =
                profileRepository.updateUserData(id, name, email, profilePictureUri, age)

            _updateDataUserResult.value = result

            result.onSuccess { updatedData ->
                _userData.value = updatedData
                clearErrorMessage()
                Log.d("ProfileViewModel", "User data updated successfully.")
            }.onFailure {
                setErrorMessage(it.message)
                Log.e("ProfileViewModel", "Failed to update user data: ${it.message}")
            }
            setLoading(false)
        }
    }

    fun postPreferences(destinationId: List<Int>) {
        setLoading(true)
        viewModelScope.launch {
            try {
                val result = profileRepository.postPreferences(destinationId)

                _postPreferenceResult.value = result
            } catch (e: Exception) {
                _postPreferenceResult.value = Result.failure(e)
            } finally {
                setLoading(false)
            }
        }
    }

    fun getPreferences() {
        setLoading(true)
        viewModelScope.launch {
            try {
                val result = profileRepository.getPreference()

                _preferencesResult.value = result

                result.onSuccess {
                    _preferencesResult.value = result
                }

            } catch (e: Exception) {
                _preferencesResult.value = Result.failure(e)
            } finally {
                setLoading(false)
            }
        }
    }
}
