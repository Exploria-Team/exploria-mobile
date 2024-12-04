package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.app.exploria.data.remote.response.AllDestinationsItem
import com.app.exploria.data.remote.response.GetDestinationByIdResponse
import com.app.exploria.data.remote.response.SearchDestinationDataItem
import com.app.exploria.data.repositories.DestinationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(private val destinationRepository: DestinationRepository) : BaseViewModel() {
    private val _destinationData = MutableStateFlow<GetDestinationByIdResponse?>(null)
    val destinationData: StateFlow<GetDestinationByIdResponse?> get() = _destinationData

    private val _listDestination = MutableStateFlow<List<SearchDestinationDataItem>?>(null)
    val listDestinationData: StateFlow<List<SearchDestinationDataItem>?> get() = _listDestination

    private val _destinations = MutableStateFlow<PagingData<AllDestinationsItem>>(PagingData.empty())
    val destinations: StateFlow<PagingData<AllDestinationsItem>> get() = _destinations

    fun fetchDestinations() {
        setLoading(true)
        viewModelScope.launch {
            destinationRepository.getDestinations().collectLatest { pagingData ->
                _destinations.value = pagingData
                setLoading(false)
            }
        }
    }

    fun getDestinationById(id: Int) {
        setLoading(true)
        viewModelScope.launch {
            val result = destinationRepository.getDestinationById(id)

            result.onSuccess { response ->
                _destinationData.value = response
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
