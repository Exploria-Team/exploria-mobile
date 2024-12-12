package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class ReviewRequest (
    @SerializedName("destinationId") val destinationId: Int,
    @SerializedName("reviewText") val reviewText: String,
    @SerializedName("rating") val rating: Int,
    @SerializedName("reviewPhotoUrl") val reviewPhotoUrl: String? = null
)