package com.app.exploria.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.app.exploria.data.remote.response.GetPlanDestinationByIdResponseItem
import com.app.exploria.data.remote.response.GetPlansResponseItem
import com.app.exploria.data.remote.response.PostPlanData
import com.app.exploria.data.remote.response.PostPlanDestinationData
import com.app.exploria.data.repositories.TravelPlanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TravelPlanViewModel @Inject constructor(private val travelPlanRepository: TravelPlanRepository) : BaseViewModel() {

    private val _plans = MutableStateFlow<List<GetPlansResponseItem>>(emptyList())
    val plans: StateFlow<List<GetPlansResponseItem>> get() = _plans

    private val _planDetails = MutableStateFlow<List<GetPlanDestinationByIdResponseItem>>(emptyList())
    val planDetails: StateFlow<List<GetPlanDestinationByIdResponseItem>> get() = _planDetails

    private val _postPlanResult = mutableStateOf<Result<PostPlanData>?>(null)
    val postPlanResult: State<Result<PostPlanData>?> = _postPlanResult

    private val _uploadDestinationResult = mutableStateOf<Result<PostPlanDestinationData>?>(null)
    val uploadDestinationResult: State<Result<PostPlanDestinationData>?> = _uploadDestinationResult

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

    fun postPlan(date: String, name: String) {
        setLoading(true)
        viewModelScope.launch {
            try {
                val result = travelPlanRepository.postPlan(date, name)
                _postPlanResult.value = result
                if (result.isSuccess) {
                    clearErrorMessage()
                } else {
                    setErrorMessage(result.exceptionOrNull()?.message)
                }
            } catch (e: Exception) {
                _postPlanResult.value = Result.failure(e)
                setErrorMessage(e.message)
            } finally {
                setLoading(false)
            }
        }
    }

    fun getPlanById(id: String) {
        setLoading(true)
        viewModelScope.launch {
            val result = travelPlanRepository.getPlanById(id)

            result.onSuccess { data ->
                _planDetails.value = data
                clearErrorMessage()
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }

    fun uploadDestinationPlan(date: String, planId: String, destinationId: Int) {
        setLoading(true)
        viewModelScope.launch {
            try {
                val result = travelPlanRepository.uploadDestinationPlan(date, planId, destinationId)
                _uploadDestinationResult.value = result
                if (result.isSuccess) {
                    clearErrorMessage()
                } else {
                    setErrorMessage(result.exceptionOrNull()?.message)
                }
            } catch (e: Exception) {
                _uploadDestinationResult.value = Result.failure(e)
                setErrorMessage(e.message)
            } finally {
                setLoading(false)
            }
        }
    }
}
