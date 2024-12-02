package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetDestinationByIdResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: DestinationByIdData
) : Parcelable

@Parcelize
data class DestinationByIdData(

	@field:SerializedName("entryFee")
	val entryFee: Int,

	@field:SerializedName("visitDurationMinutes")
	val visitDurationMinutes: Int,

	@field:SerializedName("averageRating")
	val averageRating: Float,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("lon")
	val lon: Float,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("cityId")
	val cityId: Int,

	@field:SerializedName("lat")
	val lat: Float
) : Parcelable
