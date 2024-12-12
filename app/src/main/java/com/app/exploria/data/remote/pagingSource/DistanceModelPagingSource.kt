package com.app.exploria.data.remote.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.response.DistanceModelDataItem
import retrofit2.HttpException
import java.io.IOException

class DistanceModelPagingSource(
    private val apiService: ApiService,
    private val id: Int,
    private val pageSize: Int
) : PagingSource<Int, DistanceModelDataItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DistanceModelDataItem> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getDistanceModel(id, page, pageSize)
            val data = response.data
            val nextPage = response.pagination?.let {
                if (it.currentPage < it.totalPages) it.currentPage + 1 else null
            }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            exception.printStackTrace()
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DistanceModelDataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}