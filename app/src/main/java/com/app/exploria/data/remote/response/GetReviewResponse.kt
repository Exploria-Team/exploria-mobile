package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetReviewResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: List<DataItem>
) : Parcelable

@Parcelize
data class Destination(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable

@Parcelize
data class User(

	@field:SerializedName("profilePictureUrl")
	val profilePictureUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("reviewDate")
	val reviewDate: String,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("destination")
	val destination: Destination,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("destinationId")
	val destinationId: Int,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("reviewText")
	val reviewText: String
) : Parcelable
