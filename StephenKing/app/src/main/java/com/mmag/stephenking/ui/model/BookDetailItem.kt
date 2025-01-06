package com.mmag.stephenking.ui.model

import com.mmag.stephenking.data.network.model.VillainResponse

data class BookDetailItem(
    val iSBN: String,
    val id: Int,
    val notes: List<String>,
    val pages: Int,
    val publisher: String,
    val title: String,
    val villains: List<VillainResponse>,
    val year: Int,
)
