package com.app.exploria.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.app.exploria.data.database.ItinerariesDatabase
import com.app.exploria.data.database.dao.ItinerariesDao
import com.app.exploria.data.pref.UserPreference
import com.app.exploria.data.remote.api.ApiConfig
import com.app.exploria.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Named
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ItinerariesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ItinerariesDatabase::class.java,
            "itineraries_database"
        ).build()
    }

    @Provides
    fun provideItinerariesDao(database: ItinerariesDatabase): ItinerariesDao {
        return database.itinerariesDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideUserPreferences(database: DataStore<Preferences>): UserPreference {
        return UserPreference(database)
    }

    @Provides
    @Singleton
    fun provideApiService(apiConfig: ApiConfig): ApiService {
        return apiConfig.provideApiService()
    }

    @Provides
    @Singleton
    @Named("AuthToken")
    fun provideAuthToken(userPreference: UserPreference): String {
        val token = runBlocking { userPreference.getSession().firstOrNull()?.token }
        return token ?: throw IllegalStateException("Auth token is not available")
    }

    @Provides
    @Singleton
    @Named("ApiServiceWithToken")
    fun provideApiServiceWithToken(apiConfig: ApiConfig, @Named("AuthToken") token: String): ApiService {
        return apiConfig.provideApiServiceWithToken(token)
    }

}