package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PlanResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: PlanData
) : Parcelable

@Parcelize
data class PlanData(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("userId")
	val userId: String
) : Parcelable
