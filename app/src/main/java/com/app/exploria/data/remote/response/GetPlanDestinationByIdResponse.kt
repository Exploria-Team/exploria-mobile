package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetPlanDestinationByIdResponse(

	@field:SerializedName("GetPlanDestinationByIdResponse")
	val getPlanDestinationByIdResponse: List<GetPlanDestinationByIdResponseItem>
) : Parcelable

@Parcelize
data class GetPlanDestinationByIdDestination(

	@field:SerializedName("entryFee")
	val entryFee: String,

	@field:SerializedName("visitDurationMinutes")
	val visitDurationMinutes: Int,

	@field:SerializedName("averageRating")
	val averageRating: Float,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("cityId")
	val cityId: Int,

	@field:SerializedName("lat")
	val lat: Double
) : Parcelable

@Parcelize
data class GetPlanDestinationByIdResponseItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("destination")
	val destination: GetPlanDestinationByIdDestination,

	@field:SerializedName("planId")
	val planId: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("destinationId")
	val destinationId: Int
) : Parcelable
