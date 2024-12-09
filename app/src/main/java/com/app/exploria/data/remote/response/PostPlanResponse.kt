package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PostPlanResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: PostPlanData
) : Parcelable

@Parcelize
data class PostPlanData(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("totalDays")
	val totalDays: Int,

	@field:SerializedName("endDate")
	val endDate: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("startDate")
	val startDate: String
) : Parcelable
