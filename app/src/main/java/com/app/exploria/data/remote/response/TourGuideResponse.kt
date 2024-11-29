package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class TourGuideResponse<T>(
	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data")
	val data: T
)

data class TourGuide(
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("waNumber")
	val waNumber: String? = null,

	@field:SerializedName("preferences")
	val preferences: List<Preference>? = null
)

data class Preference(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("group")
	val group: String
)