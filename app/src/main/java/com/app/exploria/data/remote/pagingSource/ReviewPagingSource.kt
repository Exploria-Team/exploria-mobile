package com.app.exploria.data.remote.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.response.GetReviewResponse
import com.app.exploria.data.remote.response.ReviewsItem
import retrofit2.HttpException
import java.io.IOException

class ReviewPagingSource(
    private val apiService: ApiService,
    private val id: Int,
    private val pageSize: Int
) : PagingSource<Int, ReviewsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewsItem> {
        val page = params.key ?: 1
        return try {
            val response: GetReviewResponse = apiService.getReviews(id, page, pageSize)
            val data = response.data.reviews
            val nextPage = if (response.data.pagination.currentPage < response.data.pagination.totalPages) {
                response.data.pagination.currentPage + 1
            } else {
                null
            }
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ReviewsItem>): Int? {
        // Return the page key to refresh
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
