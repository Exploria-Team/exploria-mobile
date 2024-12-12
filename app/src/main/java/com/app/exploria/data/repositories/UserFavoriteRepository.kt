package com.app.exploria.data.repositories

import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.request.FavoriteRequest
import com.app.exploria.data.remote.response.GetAllUserFavoriteDataItem
import com.app.exploria.data.remote.response.PostFavoriteResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UserFavoriteRepository @Inject constructor(
    @Named("ApiServiceWithToken") private val apiService: ApiService
) {
    suspend fun toggleFavorite(destinationId: Int): Result<PostFavoriteResponse> {
        return try {
            val request = FavoriteRequest(destinationId)
            val response = apiService.favoriteUser(request)
            if (response.statusCode in 200..299) {
                Result.success(response)
            } else {
                Result.failure(Exception("Error: ${response.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllFavorites(): Result<List<GetAllUserFavoriteDataItem>> {
        return try {
            val response = apiService.getAllUserFavorite()
            if (response.statusCode == 200) {
                Result.success(response.data ?: emptyList())
            } else {
                Result.failure(Exception("Error fetching favorites: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}