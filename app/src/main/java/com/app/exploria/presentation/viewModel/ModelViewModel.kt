package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.app.exploria.data.remote.response.DistanceModelDataItem
import com.app.exploria.data.remote.response.NormalModelDataItem
import com.app.exploria.data.repositories.ModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModelViewModel @Inject constructor(
    private val modelRepository: ModelRepository
) : BaseViewModel() {
    private val _normalModelData = MutableStateFlow<PagingData<NormalModelDataItem>>(PagingData.empty())
    val normalModelData : StateFlow<PagingData<NormalModelDataItem>> get() = _normalModelData

    private val _distanceModelData = MutableStateFlow<List<DistanceModelDataItem>>(emptyList())
    val distanceModelData : StateFlow<List<DistanceModelDataItem>> get() = _distanceModelData

    fun fetchNormalModel() {
        setLoading(true)
        viewModelScope.launch {
            modelRepository.getNormalModel().collectLatest { pagingData ->
                _normalModelData.value = pagingData
                setLoading(false)
            }
        }
    }

    fun getDistanceModel(id : Int) {
        setLoading(true)
        viewModelScope.launch {
            val result = modelRepository.getDistanceModel(id)

            result.onSuccess { data ->
                _distanceModelData.value = data
            }.onFailure {
                setErrorMessage(it.message)
            }
            setLoading(false)
        }
    }
}