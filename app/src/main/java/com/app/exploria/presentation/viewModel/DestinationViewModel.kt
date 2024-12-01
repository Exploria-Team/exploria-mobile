package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.app.exploria.data.remote.response.DestinationResponse
import com.app.exploria.data.repositories.DestinationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(private val destinationRepository: DestinationRepository) : BaseViewModel() {
    private val _destinationData = MutableStateFlow<DestinationResponse?>(null)
    val destinationData: StateFlow<DestinationResponse?> get() = _destinationData

    private val _listDestination = MutableStateFlow<List<DestinationResponse>?>(null)
    val listDestinationData: StateFlow<List<DestinationResponse>?> get() = _listDestination


    fun getDestinationById(id: Int) {
        setLoading(true)
        viewModelScope.launch {
            val result = destinationRepository.getDestinationById(id)

            result.onSuccess { data ->
                _destinationData.value = data
                clearErrorMessage()
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }


    fun searchDestination(search: String) {
        setLoading(true)
        viewModelScope.launch {
            val result = destinationRepository.searchDestinations(search)

            result.onSuccess { data ->
                _listDestination.value = data
                clearErrorMessage()
            }.onFailure { error ->
                val errorMessage = "Pencarian gagal, coba lagi."
                setErrorMessage(errorMessage)
            }
            setLoading(false)
        }
    }

}