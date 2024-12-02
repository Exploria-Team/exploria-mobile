package com.app.exploria.data.repositories

import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.response.GetDestinationByIdResponse
import com.app.exploria.data.remote.response.SearchDestinationDataItem
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class DestinationRepository @Inject constructor(@Named("ApiServiceWithToken") private val apiService: ApiService) {

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


    suspend fun searchDestinations(search: String): Result<List<SearchDestinationDataItem>> {
        return try {
            val response = apiService.searchDestination(search)
            if (response.statusCode == 200 && !response.data.isNullOrEmpty()) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("No destinations found"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error searching destinations: ${e.message}"))
        }
    }

}