package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DistanceModelResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: List<DistanceModelDataItem>
) : Parcelable

@Parcelize
data class DistanceModelDataItem(

	@field:SerializedName("entryFee")
	val entryFee: Int,

	@field:SerializedName("averageRating")
	val averageRating: Float,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("cityId")
	val cityId: Int,

	@field:SerializedName("photos")
	val photos: List<String>
) : Parcelable
