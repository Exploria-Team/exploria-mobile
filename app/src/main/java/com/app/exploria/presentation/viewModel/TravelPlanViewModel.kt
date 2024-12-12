package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.app.exploria.data.remote.response.GetPlanDestinationByIdResponseItem
import com.app.exploria.data.remote.response.GetPlansResponseItem
import com.app.exploria.data.remote.response.PostPlanData
import com.app.exploria.data.remote.response.PostPlanDestinationData
import com.app.exploria.data.repositories.TravelPlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TravelPlanViewModel @Inject constructor(private val travelPlanRepository: TravelPlanRepository) :
    BaseViewModel() {

    private val _plans = MutableStateFlow<List<GetPlansResponseItem>>(emptyList())
    val plans: StateFlow<List<GetPlansResponseItem>> get() = _plans

    private val _planDetails =
        MutableStateFlow<List<GetPlanDestinationByIdResponseItem>>(emptyList())
    val planDetails: StateFlow<List<GetPlanDestinationByIdResponseItem>> get() = _planDetails

    private val _postPlanResult = MutableStateFlow<PostPlanData?>(null)
    val postPlanResult: StateFlow<PostPlanData?> = _postPlanResult

    private val _uploadDestinationResult = MutableStateFlow<PostPlanDestinationData?>(null)
    val uploadDestinationResult: StateFlow<PostPlanDestinationData?> = _uploadDestinationResult

    fun getPlans() {
        setLoading(true)
        viewModelScope.launch {
            val result = travelPlanRepository.getPlans()
            result.onSuccess { data ->
                _plans.value = data
                clearErrorMessage()
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }

    fun postPlan(name: String, startDate: String, endDate: String) {
        setLoading(true)
        viewModelScope.launch {
            val result = travelPlanRepository.postPlan(name, startDate, endDate)
            result.onSuccess { data ->
                _postPlanResult.value = data
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }

    fun fetchPlanDetails(id: String) {
        setLoading(true)
        viewModelScope.launch {
            val result = travelPlanRepository.getPlanById(id)
            result.onSuccess { data ->
                _planDetails.value = data
            }.onFailure {
                println("Failed to fetch plan details: ${it.message}") // Debug
            }
            setLoading(false)
        }
    }

    fun uploadDestinationPlan(date: String, planId: String, destinationId: Int) {
        setLoading(true)
        viewModelScope.launch {
            val result = travelPlanRepository.uploadDestinationPlan(date, planId, destinationId)
            result.onSuccess { data ->
                _uploadDestinationResult.value = data
                clearErrorMessage()
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }
}
