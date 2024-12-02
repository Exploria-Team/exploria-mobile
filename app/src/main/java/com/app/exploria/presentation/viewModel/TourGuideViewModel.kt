package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.app.exploria.data.remote.response.GetTourGuideByIdData
import com.app.exploria.data.remote.response.GetTourGuidesData
import com.app.exploria.data.remote.response.SearchTourGuideData
import com.app.exploria.data.repositories.TourGuideRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TourGuideViewModel @Inject constructor(private val tourGuideRepository: TourGuideRepository) : BaseViewModel() {

    private val _listTourGuide = MutableStateFlow<List<SearchTourGuideData>>(emptyList())
    val listTourGuide: StateFlow<List<SearchTourGuideData>> get() = _listTourGuide

    private val _allTourGuide = MutableStateFlow<List<GetTourGuidesData>>(emptyList())
    val allTourGuide: StateFlow<List<GetTourGuidesData>> get() = _allTourGuide

    private val _selectedTourGuide = MutableStateFlow<GetTourGuideByIdData?>(null)
    val selectedTourGuide: StateFlow<GetTourGuideByIdData?> get() = _selectedTourGuide

    fun searchTourGuide(search: String) {
        setLoading(true)
        viewModelScope.launch {
            val result = tourGuideRepository.searchTourGuide(search)
            result.onSuccess { data ->
                _listTourGuide.value = data
                clearErrorMessage()
            }.onFailure { exception ->
                setErrorMessage(exception.message ?: "An error occurred while searching for tour guides.")
            }
            setLoading(false)
        }
    }

    fun getTourGuideById(id: String) {
        setLoading(true)
        viewModelScope.launch {
            val result = tourGuideRepository.getTourGuideById(id)
            result.onSuccess { data ->
                _selectedTourGuide.value = data
                clearErrorMessage()
            }.onFailure { exception ->
                setErrorMessage(exception.message ?: "Failed to fetch the tour guide.")
            }
            setLoading(false)
        }
    }

    fun loadAllTourGuides() {
        setLoading(true)
        viewModelScope.launch {
            val result = tourGuideRepository.getAllTourGuides()
            result.onSuccess { data ->
                _allTourGuide.value = data
                clearErrorMessage()
            }.onFailure { exception ->
                setErrorMessage(exception.message ?: "Failed to load tour guides.")
            }
            setLoading(false)
        }
    }
}
