package com.app.exploria.presentation.viewModel

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
    val PostPreferenceResult: State<Result<PreferenceResponse>?> = _postPreferenceResult

    private val _preferencesResult =
        mutableStateOf<Result<List<GetPreferenceDataItem>>>(Result.success(emptyList()))
    val preferencesResult: State<Result<List<GetPreferenceDataItem>>> get() = _preferencesResult


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
