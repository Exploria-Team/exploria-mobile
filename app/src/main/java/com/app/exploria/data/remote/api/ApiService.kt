package com.app.exploria.data.remote.api

import com.app.exploria.data.remote.request.FavoriteRequest
import com.app.exploria.data.remote.request.LoginRequest
import com.app.exploria.data.remote.request.PlanDestinationRequest
import com.app.exploria.data.remote.request.PreferencesRequest
import com.app.exploria.data.remote.request.RegisterRequest
import com.app.exploria.data.remote.request.ReviewRequest
import com.app.exploria.data.remote.response.DestinationListResponse
import com.app.exploria.data.remote.response.DestinationResponse
import com.app.exploria.data.remote.response.FavoriteResponse
import com.app.exploria.data.remote.response.LoginResponse
import com.app.exploria.data.remote.response.RegisterResponse
import com.app.exploria.data.remote.response.TourGuide
import com.app.exploria.data.remote.response.TourGuideResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Auth
    @POST("auth/signup")
    suspend fun register(@Body registerRequest: RegisterRequest) : RegisterResponse

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    // User
    @PUT("user/{id}")
    suspend fun updateUser(
        @Path("id") id: String
    )

    @POST("user/favorite")
    suspend fun favoriteUser(
        @Body favoriteRequest: FavoriteRequest
    )

    @GET("user/favorite")
    suspend fun getAllUserFavorite(): FavoriteResponse

    @POST("user/preference")
    suspend fun preference(
        @Body preferencesRequest: PreferencesRequest
    )

    @GET("user/preference")
    suspend fun getPreferences()

    // Destination
    @GET("destination/{id}")
    suspend fun getDestination(@Path("id") id: Int) : DestinationResponse

    @GET("destination/search")
    suspend fun searchDestination(@Query("search") search : String) : DestinationListResponse

    // Review
    @GET("review")
    suspend fun getReview()

    @POST("review")
    suspend fun submitReview(
        @Body reviewRequest: ReviewRequest
    )

    // Tour Guide
    @GET("tour-guides")
    suspend fun searchTourGuide(@Query("search") search: String): TourGuideResponse<List<TourGuide>>

    @GET("tour-guides/{id}")
    suspend fun getTourGuideById(@Path("id") id: String): TourGuideResponse<TourGuide>

    @GET("tour-guides")
    suspend fun getAllTourGuides(): TourGuideResponse<List<TourGuide>>

    // Travel Plan
    @GET("travel-plan")
    suspend fun getPlans()

    @POST("travel-plan")
    suspend fun uploadPlan()

    @POST("travel-plan/destination")
    suspend fun uploadDestinationPlan(
        @Body planDestinationRequest: PlanDestinationRequest
    )
}