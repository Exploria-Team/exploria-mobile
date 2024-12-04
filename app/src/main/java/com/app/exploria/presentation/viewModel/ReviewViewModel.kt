package com.app.exploria.presentation.viewModel

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
class ReviewViewModel @Inject constructor(private val reviewRepository: ReviewRepository) : BaseViewModel() {

    private val _submitReviewResult = MutableStateFlow<Result<PostReviewData>?>(null)
    val submitReviewResult: StateFlow<Result<PostReviewData>?> = _submitReviewResult

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


    // Fungsi untuk submit review
    fun submitReview(destinationId: Int, reviewText: String, rating: Int) {
        setLoading(true)
        viewModelScope.launch {
            try {
                val result = reviewRepository.submitReview(destinationId, reviewText, rating)

                // Menyimpan hasil pengiriman review
                _submitReviewResult.value = result
                if (result.isSuccess) {
                    clearErrorMessage()  // Jika sukses, clear error message
                } else {
                    setErrorMessage(result.exceptionOrNull()?.message)  // Tampilkan pesan error jika gagal
                }
            } catch (e: Exception) {
                _submitReviewResult.value = Result.failure(e)  // Menangani error dengan baik
                setErrorMessage(e.message)  // Menampilkan pesan error
            } finally {
                setLoading(false)  // Pastikan loading selesai setelah request selesai
            }
        }
    }
}
