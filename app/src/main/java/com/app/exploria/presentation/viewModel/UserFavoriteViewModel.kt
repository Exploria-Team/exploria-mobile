package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.app.exploria.data.remote.response.GetAllUserFavoriteDataItem
import com.app.exploria.data.repositories.UserFavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFavoriteViewModel @Inject constructor(
    private val userFavoriteRepository: UserFavoriteRepository
) : BaseViewModel() {

    private val _favorites = MutableStateFlow<List<GetAllUserFavoriteDataItem>>(emptyList())
    val favorites: StateFlow<List<GetAllUserFavoriteDataItem>> get() = _favorites

    fun toggleFavorite(destinationId: Int) {
        viewModelScope.launch {
            setLoading(true)
            val result = userFavoriteRepository.toggleFavorite(destinationId)
            result.onSuccess {
                getAllFavorites()
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
                _favorites.value = data.distinctBy { it.destination.id }
                clearErrorMessage()
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }
}
