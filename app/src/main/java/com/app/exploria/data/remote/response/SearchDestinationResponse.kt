package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class SearchDestinationResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: List<SearchDestinationDataItem>
) : Parcelable

@Parcelize
data class SearchDestinationDataItem(

	@field:SerializedName("averageRating")
	val averageRating: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("lon")
	val lon: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("cityId")
	val cityId: Int,

	@field:SerializedName("lat")
	val lat: String
) : Parcelable
