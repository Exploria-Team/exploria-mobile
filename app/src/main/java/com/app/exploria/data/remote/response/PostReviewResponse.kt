package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class PostReviewResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: Data
)

data class Data(

	@field:SerializedName("reviewDate")
	val reviewDate: String,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("destinationId")
	val destinationId: Int,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("reviewText")
	val reviewText: String
)
