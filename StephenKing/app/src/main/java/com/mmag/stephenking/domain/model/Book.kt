package com.mmag.stephenking.domain.model


data class Book(
    val createdAt: String,
    val handle: String,
    val iSBN: String,
    val id: Int,
    val notes: List<String>,
    val pages: Int,
    val publisher: String,
    val title: String,
    val villains: List<Villain>,
    val year: Int,
)