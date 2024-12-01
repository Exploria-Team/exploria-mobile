package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class PlanDestinationResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("message")
	val message: List<MessageItem>
)

data class MessageItem(

	@field:SerializedName("path")
	val path: List<String>,

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("validation")
	val validation: String
)
