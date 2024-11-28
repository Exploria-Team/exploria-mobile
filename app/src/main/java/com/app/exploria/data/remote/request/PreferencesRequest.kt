package com.app.exploria.data.remote.request

import com.google.gson.annotations.SerializedName

data class PreferencesRequest(
    @SerializedName("preferences") val preferences: List<Int>
)