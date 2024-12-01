package com.app.exploria.data.repositories

import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.response.DestinationResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class DestinationRepository @Inject constructor(    @Named("ApiServiceWithToken") private val apiService: ApiService)
{

    suspend fun getDestinationById(id: Int): Result<DestinationResponse> {
        return try {
            val response = apiService.getDestination(id)

            if (response.statusCode == 200 && response.data != null && response.data.isNotEmpty()) {
                Result.success(response.data[0])
            } else {
                Result.failure(Exception("No destination found for ID: $id"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching destination: ${e.message}"))
        }
    }

    suspend fun searchDestinations(search: String): Result<List<DestinationResponse>> {
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