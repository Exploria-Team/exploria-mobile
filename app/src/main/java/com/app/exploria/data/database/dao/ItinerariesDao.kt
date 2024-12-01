package com.app.exploria.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.exploria.data.database.entities.ItinerariesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItinerariesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItinerary(itinerary: ItinerariesEntity)

    @Delete
    suspend fun deleteItinerary(itinerary: ItinerariesEntity)

    @Query("SELECT * from itineraries_database")
    fun getAllItineraries(): Flow<List<ItinerariesEntity>>

    @Query("SELECT * from itineraries_database WHERE id= :id")
    fun getItineraryById(id: Int): Flow<ItinerariesEntity>
}