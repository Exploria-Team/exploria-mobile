package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class FavoriteRequest(
    @SerializedName("destinationId") val destinationId: Int
)
