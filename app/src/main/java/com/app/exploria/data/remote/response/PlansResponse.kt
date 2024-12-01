package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PlansResponse(

	@field:SerializedName("PlansResponse")
	val plansResponse: List<PlansResponseItem>
) : Parcelable

@Parcelize
data class PlansResponseItem(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("userId")
	val userId: String
) : Parcelable
