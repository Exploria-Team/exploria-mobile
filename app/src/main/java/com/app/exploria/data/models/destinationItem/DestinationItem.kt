package com.app.exploria.data.models.destinationItem

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DestinationItem(
    val name: String,
    val date: String,
    val id: String
) : Parcelable
