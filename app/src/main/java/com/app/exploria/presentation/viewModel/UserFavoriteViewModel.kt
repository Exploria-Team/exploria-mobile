package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.app.exploria.data.remote.response.DataItem
import com.app.exploria.data.repositories.UserFavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserFavoriteViewModel @Inject constructor(private val userFavoriteRepository: UserFavoriteRepository): BaseViewModel() {
    private val _favorites = MutableStateFlow<List<DataItem>>(emptyList())
    val Favorites: StateFlow<List<DataItem>> get() = _favorites

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
}