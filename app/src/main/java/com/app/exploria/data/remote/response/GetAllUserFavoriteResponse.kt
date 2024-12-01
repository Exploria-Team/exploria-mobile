package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetAllUserFavoriteResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: List<DataItem>
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("destination")
	val destination: Destination,

	@field:SerializedName("id")
	val id: Int
) : Parcelable

@Parcelize
data class Destination(

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("averageRating")
	val averageRating: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable
