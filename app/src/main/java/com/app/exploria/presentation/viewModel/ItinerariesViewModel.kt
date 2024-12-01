package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.app.exploria.data.database.entities.ItinerariesEntity
import com.app.exploria.data.repositories.ItinerariesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItinerariesViewModel @Inject constructor(private val itinerariesRepository: ItinerariesRepository) : BaseViewModel() {

    private val _itineraries = MutableStateFlow<List<ItinerariesEntity>>(emptyList())
    val itineraries: StateFlow<List<ItinerariesEntity>> get() = _itineraries

    fun fetchItineraries() {
        setLoading(true)
        viewModelScope.launch {
            try {
                itinerariesRepository.fetchAndCacheItineraries()
                _itineraries.value = itinerariesRepository.getAllItineraries().firstOrNull() ?: emptyList()
                clearErrorMessage()
            } catch (e: Exception) {
                setErrorMessage("Error fetching itineraries: ${e.localizedMessage}")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getAllItineraries() {
        viewModelScope.launch {
            _itineraries.value = itinerariesRepository.getAllItineraries().firstOrNull() ?: emptyList()
        }
    }

    fun insertItineraryDao(itinerariesEntity: ItinerariesEntity) {
        setLoading(true)
        viewModelScope.launch {
            try {
                itinerariesRepository.insertItinerary(itinerariesEntity)
                clearErrorMessage()
            } catch (e: Exception) {
                setErrorMessage("Error inserting itinerary: ${e.localizedMessage}")
            } finally {
                setLoading(false)
            }
        }
    }

    fun deleteItineraryDao(itinerariesEntity: ItinerariesEntity) {
        setLoading(true)
        viewModelScope.launch {
            try {
                itinerariesRepository.deleteItinerary(itinerariesEntity)
                clearErrorMessage()
            } catch (e: Exception) {
                setErrorMessage("Error deleting itinerary: ${e.localizedMessage}")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getAllItinerariesDao() {
        setLoading(true)
        viewModelScope.launch {
            try {
                val itineraries = itinerariesRepository.getAllItineraries()
                clearErrorMessage()
            } catch (e: Exception) {
                setErrorMessage("Error fetching itineraries: ${e.localizedMessage}")
            } finally {
                setLoading(false)
            }
        }
    }
}
