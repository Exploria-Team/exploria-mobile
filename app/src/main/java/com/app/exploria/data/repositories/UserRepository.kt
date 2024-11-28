package com.app.exploria.data.repositories

import com.app.exploria.data.models.userData.UserModel
import com.app.exploria.data.pref.UserPreference
import com.app.exploria.data.remote.api.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
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
}