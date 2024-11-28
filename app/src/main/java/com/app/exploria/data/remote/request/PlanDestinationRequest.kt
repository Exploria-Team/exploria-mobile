package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class PlanDestinationRequest (
    @SerializedName("planId") val planId: Int,
    @SerializedName("destinationId") val destinationId: Int,
    @SerializedName("date") val date: String
)