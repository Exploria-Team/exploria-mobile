package com.app.exploria.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetPlansResponse(

	@field:SerializedName("GetPlanResponse")
	val getPlanResponse: List<GetPlansResponseItem>
) : Parcelable

@Parcelize
data class GetPlansResponseItem(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("userId")
	val userId: Int
) : Parcelable
