package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetAllUserFavoriteResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: List<GetAllUserFavoriteDataItem>
) : Parcelable

@Parcelize
data class GetAllUserFavoriteDataItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("destination")
	val destination: GetAllUserFavoriteDestination,

	@field:SerializedName("id")
	val id: Int
) : Parcelable

@Parcelize
data class GetAllUserFavoriteDestination(

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("averageRating")
	val averageRating: Float,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable
