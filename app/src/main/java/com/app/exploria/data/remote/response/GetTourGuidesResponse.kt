package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetTourGuidesResponse(

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("message")
	val message: String
) : Parcelable
