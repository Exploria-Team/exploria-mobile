package com.app.exploria.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.pagingSource.DistanceModelPagingSource
import com.app.exploria.data.remote.pagingSource.NormalModelPagingSource
import com.app.exploria.data.remote.response.DistanceModelDataItem
import com.app.exploria.data.remote.response.NormalModelDataItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ModelRepository @Inject constructor(
    @Named("ApiServiceWithToken") private val apiService: ApiService
) {

    fun getNormalModel(pageSize: Int = 4): Flow<PagingData<NormalModelDataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true,
                maxSize = 200
            ),
            pagingSourceFactory = { NormalModelPagingSource(apiService, pageSize) }
        ).flow
    }

    fun getDistanceModel(id: Int, pageSize: Int = 4): Flow<PagingData<DistanceModelDataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
                maxSize = 200
            ),
            pagingSourceFactory = { DistanceModelPagingSource(apiService, id, pageSize) }
        ).flow
    }
}

