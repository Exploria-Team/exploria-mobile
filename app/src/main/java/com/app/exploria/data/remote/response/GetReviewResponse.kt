package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetReviewResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: List<Any>
)
