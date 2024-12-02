package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PlanDestinationResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: PlanDestinationData
) : Parcelable

@Parcelize
data class PlanDestinationData(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("planId")
	val planId: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("destinationId")
	val destinationId: Int
) : Parcelable
