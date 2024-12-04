package com.app.exploria.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.app.exploria.data.remote.response.GetAllUserFavoriteDataItem
import com.app.exploria.data.remote.response.GetPreferenceDataItem
import com.app.exploria.data.remote.response.PreferenceResponse
import com.app.exploria.data.repositories.UserFavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserFavoriteViewModel @Inject constructor(private val userFavoriteRepository: UserFavoriteRepository) :
    BaseViewModel() {
    private val _favorites = MutableStateFlow<List<GetAllUserFavoriteDataItem>>(emptyList())
    val Favorites: StateFlow<List<GetAllUserFavoriteDataItem>> get() = _favorites

    private val _postPreferenceResult = mutableStateOf<Result<PreferenceResponse>?>(null)
    val PostPreferenceResult: State<Result<PreferenceResponse>?> = _postPreferenceResult

    private val _preferencesResult =
        mutableStateOf<Result<List<GetPreferenceDataItem>>>(Result.success(emptyList()))
    val preferencesResult: State<Result<List<GetPreferenceDataItem>>> get() = _preferencesResult


    fun toggleFavorite(destinationId: Int) {
        setLoading(true)
        viewModelScope.launch {
            val result = userFavoriteRepository.toggleFavorite(destinationId)

            result.onSuccess {
                clearErrorMessage()
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }

    fun getAllFavorites() {
        setLoading(true)
        viewModelScope.launch {
            val result = userFavoriteRepository.getAllFavorites()

            result.onSuccess { data ->
                _favorites.value = data
                clearErrorMessage()
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }

    fun postPreference(destinationId: Int) {
        setLoading(true)
        viewModelScope.launch {
            try {
                val result = userFavoriteRepository.postPreference(destinationId)

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
                val result = userFavoriteRepository.getPreference()

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