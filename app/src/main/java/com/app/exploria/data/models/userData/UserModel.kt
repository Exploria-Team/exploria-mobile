package com.app.exploria.data.models.userData

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("token") val token: String,
    @SerializedName("profilePictureUrl") val profilePictureUrl: String?,
    @SerializedName("isLogin") val isLogin: Boolean = false
)