package com.app.exploria.data.remote.api

import com.app.exploria.data.remote.request.FavoriteRequest
import com.app.exploria.data.remote.request.LoginRequest
import com.app.exploria.data.remote.request.PlanDestinationRequest
import com.app.exploria.data.remote.request.PlanRequest
import com.app.exploria.data.remote.request.PreferencesRequest
import com.app.exploria.data.remote.request.RegisterRequest
import com.app.exploria.data.remote.request.ReviewRequest
import com.app.exploria.data.remote.request.UserDataRequest
import com.app.exploria.data.remote.response.DistanceModelResponse
import com.app.exploria.data.remote.response.GetAllDestinationResponse
import com.app.exploria.data.remote.response.GetAllUserFavoriteResponse
import com.app.exploria.data.remote.response.GetDestinationByIdResponse
import com.app.exploria.data.remote.response.GetPlanDestinationByIdResponse
import com.app.exploria.data.remote.response.GetPlanDestinationByIdResponseItem
import com.app.exploria.data.remote.response.GetPlansResponse
import com.app.exploria.data.remote.response.GetPreferenceResponse
import com.app.exploria.data.remote.response.GetReviewResponse
import com.app.exploria.data.remote.response.GetTourGuideByIdResponse
import com.app.exploria.data.remote.response.GetTourGuidesResponse
import com.app.exploria.data.remote.response.LoginResponse
import com.app.exploria.data.remote.response.NormalModelResponse
import com.app.exploria.data.remote.response.PostFavoriteResponse
import com.app.exploria.data.remote.response.PostPlanDestinationResponse
import com.app.exploria.data.remote.response.PostPlanResponse
import com.app.exploria.data.remote.response.PostReviewResponse
import com.app.exploria.data.remote.response.PreferenceResponse
import com.app.exploria.data.remote.response.RegisterResponse
import com.app.exploria.data.remote.response.SearchDestinationData
import com.app.exploria.data.remote.response.SearchDestinationResponse
import com.app.exploria.data.remote.response.SearchTourGuideResponse
import com.app.exploria.data.remote.response.UserDataResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Auth
    @POST("auth/signup")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    // User
    @GET("user/{id}")
    suspend fun getUserData(
        @Path("id") id: Int
    ): UserDataResponse

    @PUT("user/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body userDataRequest: UserDataRequest
    ): UserDataResponse

    @POST("user/favorite")
    suspend fun favoriteUser(
        @Body favoriteRequest: FavoriteRequest
    ): PostFavoriteResponse

    @GET("user/favorite")
    suspend fun getAllUserFavorite(): GetAllUserFavoriteResponse

    @POST("user/preference")
    suspend fun preference(
        @Body preferencesRequest: PreferencesRequest
    ): PreferenceResponse

    @GET("user/preference")
    suspend fun getPreferences(): GetPreferenceResponse

    // Destination
    @GET("destination")
    suspend fun getAllDestinations(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): GetAllDestinationResponse

    @GET("destination/{id}")
    suspend fun getDestination(@Path("id") id: Int): GetDestinationByIdResponse

    @GET("destination/")
    suspend fun searchDestination(@Query("search") search: String): SearchDestinationResponse

    // Review
    @GET("review/destination/{id}")
    suspend fun getReviews(
        @Path("id") id: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): GetReviewResponse

    @POST("review")
    suspend fun submitReview(
        @Body reviewRequest: ReviewRequest
    ): PostReviewResponse

    // Tour Guide
    @GET("tour-guides")
    suspend fun searchTourGuide(@Query("search") search: String): SearchTourGuideResponse

    @GET("tour-guides/{id}")
    suspend fun getTourGuideById(@Path("id") id: String): GetTourGuideByIdResponse

    @GET("tour-guides")
    suspend fun getAllTourGuides(): GetTourGuidesResponse

    // Travel Plan
    @GET("travel-plan")
    suspend fun getPlans(): GetPlansResponse

    @POST("travel-plan")
    suspend fun postPlan(@Body planRequest: PlanRequest): PostPlanResponse

    @GET("travel-plan/destination/{id}")
    suspend fun getPlanById(@Path("id") id: String): List<GetPlanDestinationByIdResponseItem>

    @POST("travel-plan/destination")
    suspend fun uploadDestinationPlan(
        @Body planDestinationRequest: PlanDestinationRequest
    ): PostPlanDestinationResponse

    // Model
    @GET("recommendation/normal-hybrid")
    suspend fun getNormalModel(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : NormalModelResponse

    @GET("recommendation/distance-hybrid/{id}")
    suspend fun getDistanceModel(
        @Path("id") id: Int,
    ) : DistanceModelResponse
}