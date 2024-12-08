package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetTourGuidesResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: GetTourGuidesData
) : Parcelable

@Parcelize
data class GetTourGuidesPagination(

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
data class GetTourGuidesData(

	@field:SerializedName("tourGuides")
	val tourGuides: List<TourGuidesItem>,

	@field:SerializedName("pagination")
	val pagination: GetTourGuidesPagination
) : Parcelable

@Parcelize
data class TourGuidesItem(

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("waNumber")
	val waNumber: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("verified")
	val verified: Boolean,

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("categoryGroup")
	val categoryGroup: String
) : Parcelable
