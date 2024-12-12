package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class PreferenceRequest(

	@field:SerializedName("preferences")
	val preferences: List<Int>
)
