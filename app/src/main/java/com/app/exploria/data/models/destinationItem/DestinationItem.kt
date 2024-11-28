package com.app.exploria.data.models.destinationItem

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DestinationItem(
    @SerializedName("name") val name: String,
    @SerializedName("date") val date: String,
    @SerializedName("id") val id: Int
) : Parcelable
