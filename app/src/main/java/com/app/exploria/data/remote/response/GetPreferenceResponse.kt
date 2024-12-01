package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetPreferenceResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class DataItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("group")
	val group: String
)
