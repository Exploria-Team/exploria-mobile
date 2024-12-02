package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class FavoriteResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class FavoriteData(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("destinationId")
	val destinationId: Int,

	@field:SerializedName("userId")
	val userId: String
) : Parcelable
