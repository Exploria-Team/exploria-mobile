package com.app.exploria.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.pagingSource.DestinationPagingSource
import com.app.exploria.data.remote.response.AllDestinationsItem
import com.app.exploria.data.remote.response.GetDestinationByIdResponse
import com.app.exploria.data.remote.response.SearchDestinationDataItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DestinationRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getDestinationById(id: Int): Result<GetDestinationByIdResponse> {
        return try {
            val response = apiService.getDestination(id)

            if (response.statusCode == 200) {
                Result.success(response)
            } else {
                Result.failure(Exception("No destination found for ID: $id"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching destination: ${e.message}"))
        }
    }

    fun getDestinations(pageSize: Int = 4): Flow<PagingData<AllDestinationsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true,
                maxSize = 200
            ),
            pagingSourceFactory = { DestinationPagingSource(apiService, pageSize) }
        ).flow
    }


    suspend fun searchDestinations(search: String): Result<List<SearchDestinationDataItem>> {
        return try {
            val response = apiService.searchDestination(search)

            if (response.statusCode == 200 && response.data.destinations.isNotEmpty()) {
                Result.success(response.data.destinations)
            } else {
                Result.failure(Exception("No destinations found"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error searching destinations: ${e.message}"))
        }
    }


}