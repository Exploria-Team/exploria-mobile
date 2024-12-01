package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class PlanRequest(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("name")
	val name: String
)
