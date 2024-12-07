package com.app.exploria.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.pagingSource.NormalModelPagingSource
import com.app.exploria.data.remote.response.DistanceModelDataItem
import com.app.exploria.data.remote.response.NormalModelDataItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ModelRepository @Inject constructor(private val apiService: ApiService) {

    fun getNormalModel(pageSize: Int = 5): Flow<PagingData<NormalModelDataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true,
                maxSize = 10
            ),
            pagingSourceFactory = { NormalModelPagingSource(apiService, pageSize) }
        ).flow
    }

    suspend fun getDistanceModel(id: Int): Result<List<DistanceModelDataItem>> {
        return try {
            val response = apiService.getDistanceModel(id)

            if (response.statusCode == 200) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("No Destination found for ID: $id"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching destination: ${e.message}"))
        }
    }
}

