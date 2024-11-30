package com.app.exploria.data.repositories

import com.app.exploria.data.models.userData.UserModel
import com.app.exploria.data.pref.UserPreference
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.request.LoginRequest
import com.app.exploria.data.remote.request.RegisterRequest
import com.app.exploria.data.remote.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
) {
    suspend fun saveUserSession(userModel: UserModel) {
        userPreference.saveSession(userModel)
    }

    fun getUserSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun register(name: String, email: String, password: String): Result<Unit> {
        return try {
            apiService.register(RegisterRequest(name, email, password))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.token.isNotEmpty()) {
                Result.success(response)
            } else {
                Result.failure(Exception("Login failed: No token returned"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}