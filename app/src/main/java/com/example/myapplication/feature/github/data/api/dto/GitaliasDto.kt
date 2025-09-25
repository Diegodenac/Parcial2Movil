package com.example.myapplication.feature.github.data.api.dto
import com.google.gson.annotations.SerializedName

data class GitaliasDto(val login:String,
    @SerializedName("avatar_url") val url: String)