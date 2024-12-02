package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class DestinationResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: Data
)

data class DestinationData(

	@field:SerializedName("averageRating")
	val averageRating: Any,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("lon")
	val lon: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("cityId")
	val cityId: Int,

	@field:SerializedName("lat")
	val lat: Any
)
