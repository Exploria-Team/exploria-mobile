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
    val normalModelData: StateFlow<PagingData<NormalModelDataItem>> get() = _normalModelData

    private val _distanceModelData = MutableStateFlow<PagingData<DistanceModelDataItem>>(PagingData.empty())
    val distanceModelData: StateFlow<PagingData<DistanceModelDataItem>> get() = _distanceModelData

    private val _isDistanceMode = MutableStateFlow(false)
    val isDistanceMode: StateFlow<Boolean> get() = _isDistanceMode

    fun fetchNormalModel() {
        setLoading(true)
        viewModelScope.launch {
            modelRepository.getNormalModel().collectLatest { pagingData ->
                _normalModelData.value = pagingData
                setLoading(false)
            }
        }
    }

    fun fetchDistanceModel(id: Int) {
        setLoading(true)
        viewModelScope.launch {
            modelRepository.getDistanceModel(id).collectLatest { pagingData ->
                _distanceModelData.value = pagingData
                setLoading(false)
            }
        }
    }

    fun resetToNormalModel() {
        _isDistanceMode.value = false
        fetchNormalModel()
    }
}
