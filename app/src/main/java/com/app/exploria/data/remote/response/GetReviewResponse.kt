package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetReviewResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: GetReviewData
) : Parcelable

@Parcelize
data class GetReviewData(

	@field:SerializedName("pagination")
	val pagination: ReviewPagination,

	@field:SerializedName("reviews")
	val reviews: List<ReviewsItem>
) : Parcelable

@Parcelize
data class User(

	@field:SerializedName("profilePictureUrl")
	val profilePictureUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable

@Parcelize
data class ReviewPagination(

	@field:SerializedName("totalItems")
	val totalItems: Int,

	@field:SerializedName("totalPages")
	val totalPages: Int,

	@field:SerializedName("pageSize")
	val pageSize: Int,

	@field:SerializedName("currentPage")
	val currentPage: Int
) : Parcelable

@Parcelize
data class ReviewsItem(

	@field:SerializedName("reviewPhotoUrl")
	val reviewPhotoUrl: String,

	@field:SerializedName("reviewDate")
	val reviewDate: String,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("reviewText")
	val reviewText: String
) : Parcelable
