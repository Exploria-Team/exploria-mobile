package com.app.exploria.data.repositories

import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.request.PlanDestinationRequest
import com.app.exploria.data.remote.request.PlanRequest
import com.app.exploria.data.remote.response.GetPlanDestinationByIdResponseItem
import com.app.exploria.data.remote.response.GetPlansResponseItem
import com.app.exploria.data.remote.response.PostPlanData
import com.app.exploria.data.remote.response.PostPlanDestinationData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TravelPlanRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPlans(): Result<List<GetPlansResponseItem>> {
        return try {
            val response = apiService.getPlans()

            if (response.getPlanResponse.isNotEmpty()) {
                Result.success(response.getPlanResponse)
            } else {
                Result.failure(Exception("No plans available"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun postPlan(date: String, name: String): Result<PostPlanData> {
        return try {
            val response = apiService.postPlan(PlanRequest(date, name))
            if (response.statusCode == 200) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Error send Plan: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPlanById(id : String) : Result<List<GetPlanDestinationByIdResponseItem>> {
        return try {
            val response = apiService.getPlanById(id)

            if (response.getPlanDestinationByIdResponse.isNotEmpty()) {
                Result.success(response.getPlanDestinationByIdResponse)
            } else {
                Result.failure(Exception("No plans available"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun uploadDestinationPlan(date: String, planId: String, destinationId: Int): Result<PostPlanDestinationData> {
        return try {
            val response = apiService.uploadDestinationPlan(PlanDestinationRequest(date, planId, destinationId))
            if (response.statusCode == 200) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Error send Destination Plan: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}