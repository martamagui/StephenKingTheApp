package com.mmag.stephenking.data.network.model


import com.google.gson.annotations.SerializedName

data class VillainResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)