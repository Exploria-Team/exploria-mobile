package com.app.exploria.data.repositories

import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.response.GetTourGuideByIdData
import com.app.exploria.data.remote.response.SearchTourGuideData
import com.app.exploria.data.remote.response.TourGuidesItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TourGuideRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun searchTourGuide(search: String): Result<List<SearchTourGuideData>> {
        return try {
            val response = apiService.searchTourGuide(search)

            if (response.statusCode == 200) {
                if (response.data.isNotEmpty()) {
                    Result.success(response.data)
                } else {
                    Result.failure(Exception("No tour guides found for search: $search"))
                }
            } else {
                Result.failure(Exception("Error: ${response.statusCode} - - Tour guide not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTourGuideById(id: String): Result<GetTourGuideByIdData> {
        return try {
            val response = apiService.getTourGuideById(id)

            if (response.statusCode == 200) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Error: ${response.statusCode} - Tour guide not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun getAllTourGuides(): Result<List<TourGuidesItem>> {
        return try {
            val response = apiService.getAllTourGuides()

            if (response.statusCode == 200 && response.data.tourGuides.isNotEmpty()) {
                Result.success(response.data.tourGuides)
            } else {
                Result.failure(Exception("No tour guides available"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
