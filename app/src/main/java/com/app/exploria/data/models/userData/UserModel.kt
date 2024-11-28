package com.app.exploria.data.models.userData

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("email") val email: String,
    @SerializedName("token") val token: String,
    @SerializedName("isLogin") val isLogin: Boolean = false
)