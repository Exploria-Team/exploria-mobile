package com.app.exploria.data.repositories

import com.app.exploria.data.database.dao.ItinerariesDao
import com.app.exploria.data.database.entities.ItinerariesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItinerariesRepository @Inject constructor(private val itineraryDao: ItinerariesDao) {
    suspend fun insertItinerary(itinerary: ItinerariesEntity) {
        itineraryDao.insertItinerary(itinerary)
    }

    suspend fun deleteItinerary(itinerary: ItinerariesEntity) {
        itineraryDao.deleteItinerary(itinerary)
    }

    fun getAllItineraries() : Flow<List<ItinerariesEntity>> {
        return itineraryDao.getAllItineraries()
    }

    fun getItineraryById(id: Int?) : Flow<ItinerariesEntity> {
        return itineraryDao.getItineraryById(id)
    }
}