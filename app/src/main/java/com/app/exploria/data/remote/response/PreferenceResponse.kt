package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class PreferenceResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("message")
	val message: String
)
