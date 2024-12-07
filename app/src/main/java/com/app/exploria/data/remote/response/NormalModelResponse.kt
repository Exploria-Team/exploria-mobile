package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class NormalModelResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("pagination")
	val pagination: NormalModelPagination,

	@field:SerializedName("data")
	val data: List<NormalModelDataItem>
) : Parcelable

@Parcelize
data class NormalModelPagination(

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
data class NormalModelDataItem(

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
