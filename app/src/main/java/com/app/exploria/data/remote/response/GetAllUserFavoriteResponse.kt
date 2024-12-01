package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetAllUserFavoriteResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class Destination(

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("averageRating")
	val averageRating: Any,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int
)

data class DataItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("destination")
	val destination: Destination,

	@field:SerializedName("id")
	val id: Int
)
