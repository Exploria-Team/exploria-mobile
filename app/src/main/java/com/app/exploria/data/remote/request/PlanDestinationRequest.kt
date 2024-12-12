package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class PlanDestinationRequest(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("planId")
	val planId: String,

	@field:SerializedName("destinationId")
		val destinationId: Int
)
