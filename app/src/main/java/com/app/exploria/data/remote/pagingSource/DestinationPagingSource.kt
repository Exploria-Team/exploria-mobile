package com.app.exploria.data.remote.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.response.AllDestinationsItem
import com.app.exploria.data.remote.response.GetAllDestinationResponse
import retrofit2.HttpException
import java.io.IOException

class DestinationPagingSource(
    private val apiService: ApiService,
    private val pageSize: Int
) : PagingSource<Int, AllDestinationsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllDestinationsItem> {
        val page = params.key ?: 1 // Default page is 1
        return try {
            val response: GetAllDestinationResponse = apiService.getAllDestinations(page, pageSize)
            val data = response.data.destinations
            val nextPage = if (response.data.pagination.currentPage < response.data.pagination.totalPages) {
                response.data.pagination.currentPage + 1
            } else {
                null
            }
            LoadResult.Page(
                data = data,
                prevKey = null,  // No previous page
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AllDestinationsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
