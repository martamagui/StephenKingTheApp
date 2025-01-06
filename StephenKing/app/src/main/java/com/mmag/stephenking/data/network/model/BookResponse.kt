package com.mmag.stephenking.data.network.model


import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("handle")
    val handle: String,
    @SerializedName("ISBN")
    val iSBN: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Notes")
    val notes: List<String>,
    @SerializedName("Pages")
    val pages: Int,
    @SerializedName("Publisher")
    val publisher: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("villains")
    val villains: List<VillainResponse>,
    @SerializedName("Year")
    val year: Int
)