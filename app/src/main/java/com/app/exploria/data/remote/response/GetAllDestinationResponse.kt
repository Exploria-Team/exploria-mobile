package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetAllDestinationResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: GetAllDestinationData
) : Parcelable

@Parcelize
data class AllDestinationsItem(

	@field:SerializedName("entryFee")
	val entryFee: Int,

	@field:SerializedName("photoUrls")
	val photoUrls: List<String>,

	@field:SerializedName("visitDurationMinutes")
	val visitDurationMinutes: Int,

	@field:SerializedName("city")
	val city: String,

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

	@field:SerializedName("lat")
	val lat: Double
) : Parcelable

@Parcelize
data class GetAllDestinationData(

	@field:SerializedName("pagination")
	val pagination: AllPagination,

	@field:SerializedName("destinations")
	val destinations: List<AllDestinationsItem>
) : Parcelable

@Parcelize
data class AllPagination(

	@field:SerializedName("totalItems")
	val totalItems: Int,

	@field:SerializedName("totalPages")
	val totalPages: Int,

	@field:SerializedName("pageSize")
	val pageSize: Int,

	@field:SerializedName("currentPage")
	val currentPage: Int
) : Parcelable
