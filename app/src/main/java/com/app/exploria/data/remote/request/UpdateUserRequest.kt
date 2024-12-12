package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(

	@field:SerializedName("name")
	val name: String
)
