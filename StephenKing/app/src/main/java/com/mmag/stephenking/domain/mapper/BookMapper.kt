package com.mmag.stephenking.domain.mapper

import com.mmag.stephenking.data.network.model.BookResponse
import com.mmag.stephenking.domain.model.Book

fun BookResponse.toDomainModel(): Book {
    return Book(
        id = this.id,
        handle = this.handle,
        iSBN = this.iSBN,
        createdAt = this.createdAt,
        notes = this.notes,
        pages = this.pages,
        publisher = this.publisher,
        title = this.title,
        villains = this.villains,
        year = this.year
    )
}