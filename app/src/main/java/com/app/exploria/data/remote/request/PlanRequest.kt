package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class PlanRequest(
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("startDate")
	val startDate: String,

	@field:SerializedName("endDate")
	val endDate: String

)
