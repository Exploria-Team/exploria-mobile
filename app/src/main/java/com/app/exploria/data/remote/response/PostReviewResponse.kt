package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PostReviewResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: PostReviewData
) : Parcelable

@Parcelize
data class PostReviewData(

	@field:SerializedName("reviewDate")
	val reviewDate: String,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("destinationId")
	val destinationId: Int,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("reviewText")
	val reviewText: String
) : Parcelable
