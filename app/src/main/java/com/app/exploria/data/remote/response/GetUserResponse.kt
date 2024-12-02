package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetUserResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: GetUserData
) : Parcelable

@Parcelize
data class GetUserData(

	@field:SerializedName("profilePictureUrl")
	val profilePictureUrl: String,

	@field:SerializedName("birthdate")
	val birthdate: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String
) : Parcelable
