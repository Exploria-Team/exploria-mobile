package com.app.exploria.data.repositories

import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.request.PreferencesRequest
import com.app.exploria.data.remote.request.UserDataRequest
import com.app.exploria.data.remote.response.Data
import com.app.exploria.data.remote.response.GetPreferenceDataItem
import com.app.exploria.data.remote.response.PreferenceResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    @Named("ApiServiceWithToken") private val apiService: ApiService
) {

    suspend fun getUserData(id: Int): Result<Data> {
        return try {
            val response = apiService.getUserData(id)
            if (response.statusCode == 200 && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Failed to fetch user data. Status code: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUserData(
        id: Int,
        name: String?,
        email: String?,
        profilePictureUrl: String?,
        birthdate: String?
    ): Result<Data> {
        return try {
            val response = apiService.updateUser(
                id,
                UserDataRequest(name, email, profilePictureUrl, birthdate)
            )
            if (response.statusCode == 200 && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Update failed with status code: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun postPreference(destinationId: Int): Result<PreferenceResponse> {
        return try {
            val response = apiService.preference(PreferencesRequest(preferences = listOf(destinationId)))
            if (response.statusCode == 200) {
                Result.success(response)
            } else {
                Result.failure(Exception("Error post preference: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPreference() : Result<List<GetPreferenceDataItem>> {
        return try {
            val response = apiService.getPreferences()
            if (response.statusCode == 200) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Error post preference: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
