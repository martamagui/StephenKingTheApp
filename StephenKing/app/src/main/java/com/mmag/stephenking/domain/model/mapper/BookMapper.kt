package com.mmag.stephenking.domain.model.mapper

import com.mmag.stephenking.data.network.model.BookResponse
import com.mmag.stephenking.domain.model.Book
import okhttp3.internal.immutableListOf

fun BookResponse.toDomainModel(): Book {
    val newNotes: MutableList<String> = mutableListOf()
    this.notes.forEach {
        if (it.isNotEmpty()) {
            newNotes.add(it)
        }
    }
    return Book(
        id = this.id,
        handle = this.handle,
        iSBN = this.iSBN,
        createdAt = this.createdAt,
        notes = newNotes,
        pages = this.pages,
        publisher = this.publisher,
        title = this.title,
        villains = this.villains,
        year = this.year
    )
}