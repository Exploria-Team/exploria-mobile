package com.app.exploria.data.models.userData

data class UserModel (
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)