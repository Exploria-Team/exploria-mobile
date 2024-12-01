package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class FavoriteResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class Data(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("destinationId")
	val destinationId: Int,

	@field:SerializedName("userId")
	val userId: String
)
