package com.app.exploria.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.pagingSource.ReviewPagingSource
import com.app.exploria.data.remote.request.ReviewRequest
import com.app.exploria.data.remote.response.PostReviewData
import com.app.exploria.data.remote.response.ReviewsItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepository @Inject constructor(private val apiService: ApiService) {

    fun getReviews(id: Int, pageSize: Int = 3): Flow<PagingData<ReviewsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true,
                maxSize = 20
            ),
            pagingSourceFactory = { ReviewPagingSource(apiService, id, pageSize) }
        ).flow
    }

    suspend fun submitReview(destinationId: Int, reviewText: String, rating: Int) : Result<PostReviewData> {
        return try {
            val response = apiService.submitReview(ReviewRequest(destinationId, reviewText, rating))
            if (response.statusCode == 200) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Cant submit a Review"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}