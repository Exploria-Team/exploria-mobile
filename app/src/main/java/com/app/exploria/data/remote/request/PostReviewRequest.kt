package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class PostReviewRequest(

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("destinationId")
	val destinationId: Int,

	@field:SerializedName("reviewText")
	val reviewText: String
)
