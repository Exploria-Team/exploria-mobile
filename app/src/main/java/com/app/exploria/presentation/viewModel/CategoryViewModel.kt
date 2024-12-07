package com.app.exploria.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.app.exploria.data.repositories.CategoryRepository
import com.app.exploria.data.database.entities.CategoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) : BaseViewModel() {

    private val _categories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categories: StateFlow<List<CategoryEntity>> = _categories

    init {
        viewModelScope.launch {
            categoryRepository.saveStaticCategories()

            categoryRepository.getCategories().collect { categoryList ->
                _categories.value = categoryList
            }
        }
    }
}
