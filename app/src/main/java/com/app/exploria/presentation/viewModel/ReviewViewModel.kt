package com.app.exploria.presentation.viewModel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.app.exploria.data.remote.response.PostReviewData
import com.app.exploria.data.remote.response.ReviewsItem
import com.app.exploria.data.repositories.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(private val reviewRepository: ReviewRepository) :
    BaseViewModel() {

    private val _submitReview = MutableStateFlow<Result<PostReviewData>?>(null)
    val submitReview: StateFlow<Result<PostReviewData>?> = _submitReview

    private val _reviewData = MutableStateFlow<PagingData<ReviewsItem>>(PagingData.empty())
    val reviewData: StateFlow<PagingData<ReviewsItem>> get() = _reviewData

    fun getReviews(id: Int) {
        setLoading(true)
        viewModelScope.launch {
            reviewRepository.getReviews(id).collectLatest { pagingData ->
                _reviewData.value = pagingData
                setLoading(false)
            }
        }
    }

    fun submitReview(destinationId: Int, reviewText: String, rating: Float, photoUri: Uri?) {
        setLoading(true)
        viewModelScope.launch {
            try {
                val result = reviewRepository.submitReview(
                    destinationId,
                    reviewText,
                    rating.toInt(),
                    photoUri
                )

                _submitReview.value = result
                if (result.isSuccess) {
                    clearErrorMessage()
                } else {
                    setErrorMessage(result.exceptionOrNull()?.message)
                }
            } catch (e: Exception) {
                _submitReview.value = Result.failure(e)
                setErrorMessage(e.message)
            } finally {
                setLoading(false)
            }
        }
    }
}
