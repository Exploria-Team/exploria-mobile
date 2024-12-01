package com.app.exploria.data.repositories

import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.request.UserDataRequest
import com.app.exploria.data.remote.response.Data
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    @Named("ApiServiceWithToken") private val apiService: ApiService
) {

    suspend fun getUserData(id: String): Result<Data> {
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
        id: String,
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
}
