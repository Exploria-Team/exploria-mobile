//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.app.exploria.data.database.AppDatabase
//import com.app.exploria.data.database.entities.DestinationEntity
//import com.app.exploria.data.remote.api.ApiService
//import com.app.exploria.data.remote.response.AllDestinationsItem
//
//@OptIn(ExperimentalPagingApi::class)
//class DestinationRemoteMediator(
//    private val apiService: ApiService,
//    private val database: AppDatabase
//) : RemoteMediator<Int, DestinationEntity>() {
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, DestinationEntity>
//    ): MediatorResult {
//        val page = when (loadType) {
//            LoadType.REFRESH -> 1
//            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
//            LoadType.APPEND -> {
//                val lastItem = state.lastItemOrNull()
//                lastItem?.let { it.id + 1 } ?: 1
//            }
//        }
//
//        return try {
//            val response = apiService.getAllDestinations(page, state.config.pageSize)
//            val destinations = response.data.destinations.map {
//                DestinationEntity(
//                    id = it.id,
//                    name = it.name,
//                    city = it.city,
//                    description = it.description,
//                    photoUrl = it.photoUrls.firstOrNull() ?: "",
//                    entryFee = it.entryFee,
//                    visitDurationMinutes = it.visitDurationMinutes,
//                    averageRating = it.averageRating,
//                    lat = it.lat,
//                    lon = it.lon
//                )
//            }
//
//            database.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    database.destinationDao().clearDestinations()
//                }
//                database.destinationDao().insertDestinations(destinations)
//            }
//
//            MediatorResult.Success(endOfPaginationReached = destinations.isEmpty())
//        } catch (e: Exception) {
//            MediatorResult.Error(e)
//        }
//    }
//}
