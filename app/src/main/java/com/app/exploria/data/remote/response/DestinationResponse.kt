package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class DestinationResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("averageRating")
    val averageRating: Float,

    @SerializedName("lat")
    val lat: Double,

    @SerializedName("lon")
    val lon: Double,

    @SerializedName("cityId")
    val cityId: Int
)

data class DestinationListResponse(
    @SerializedName("status_code")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String? = null,

    @SerializedName("data")
    val data: List<DestinationResponse>?
)