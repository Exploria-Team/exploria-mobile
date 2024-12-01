package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetTourGuideResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("message")
	val message: String
)
