package com.app.exploria.data.repositories

import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.request.FavoriteRequest
import com.app.exploria.data.remote.request.PreferencesRequest
import com.app.exploria.data.remote.response.GetAllUserFavoriteDataItem
import com.app.exploria.data.remote.response.GetPreferenceDataItem
import com.app.exploria.data.remote.response.PreferenceResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserFavoriteRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun toggleFavorite(destinationId: Int): Result<Unit> {
        return try {
            val request = FavoriteRequest(destinationId)
            apiService.favoriteUser(request)
            Result.success(Unit)
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