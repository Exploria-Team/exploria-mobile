package com.app.exploria.data.remote.request

data class UserDataRequest (
    val name : String?,
    val email : String?,
    val profilePictureUrl : String?,
    val birthdate : String?
)