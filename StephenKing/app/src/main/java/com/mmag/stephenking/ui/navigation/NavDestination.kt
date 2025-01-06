package com.mmag.stephenking.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object BookListDestination

@Serializable
data class BookDetailDestination(val bookId: String)