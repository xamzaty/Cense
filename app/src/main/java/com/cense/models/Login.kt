package com.cense.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("login")
    @Expose
    private val login: String,

    @SerializedName("password")
    @Expose
    private val password: String,

    @SerializedName("phone")
    @Expose
    private val phone: String,

    @SerializedName("code")
    @Expose
    private val code: String
)
