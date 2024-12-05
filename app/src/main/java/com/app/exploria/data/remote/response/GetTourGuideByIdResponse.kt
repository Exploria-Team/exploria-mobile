package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetTourGuideByIdResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: GetTourGuideByIdData
) : Parcelable

@Parcelize
data class GetTourGuideByIdData(

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

	@field:SerializedName("categoryGroup")
	val categoryGroup: String
) : Parcelable
