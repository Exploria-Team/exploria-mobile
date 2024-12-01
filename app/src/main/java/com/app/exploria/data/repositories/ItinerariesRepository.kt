package com.app.exploria.data.repositories

import com.app.exploria.data.database.dao.ItinerariesDao
import com.app.exploria.data.database.entities.ItinerariesEntity
import com.app.exploria.data.remote.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItinerariesRepository @Inject constructor(private val itineraryDao: ItinerariesDao,private val apiService: ApiService) {

    suspend fun fetchAndCacheItineraries() {
//        try {
//            val itinerariesFromApi = apiService.getPreferences()
//
//            val itinerariesEntities = itinerariesFromApi.map { itinerary ->
//                ItinerariesEntity(
//                    id = itinerary.id,
//                    name = itinerary.name,
//                    startdate = itinerary.startdate,
//                    finishdate = itinerary.finishdate,
//                    destinations = itinerary.destinations
//                )
//            }
//            itineraryDao.insertItinerary(itinerariesEntities)
//        } catch (e: Exception) {
//            throw Exception("Error fetching itineraries from API: ${e.localizedMessage}")
//        }
    }

    fun getAllItineraries(): Flow<List<ItinerariesEntity>> {
        return itineraryDao.getAllItineraries()
    }

    suspend fun getItineraryById(id: Int): ItinerariesEntity? {
        return itineraryDao.getItineraryById(id).firstOrNull()
    }

    suspend fun insertItinerary(itinerary: ItinerariesEntity) {
        itineraryDao.insertItinerary(itinerary)
    }

    suspend fun deleteItinerary(itinerary: ItinerariesEntity) {
        itineraryDao.deleteItinerary(itinerary)
    }
}