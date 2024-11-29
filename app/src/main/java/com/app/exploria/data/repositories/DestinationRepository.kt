package com.app.exploria.data.repositories

import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.response.DestinationResponse

class DestinationRepository(private val apiService: ApiService) {

    suspend fun getDestinationById(id: Int): Result<DestinationResponse> {
        return try {
            val response = apiService.getDestination(id)
            Result.success(response)
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