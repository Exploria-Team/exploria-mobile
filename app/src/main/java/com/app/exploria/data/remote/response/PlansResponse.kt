package com.app.exploria.data.remote.response

import com.google.gson.annotations.SerializedName

data class PlansRespone(

	@field:SerializedName("PlansRespone")
	val plansRespone: List<PlansResponeItem>
)

data class PlansResponeItem(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("userId")
	val userId: String
)
