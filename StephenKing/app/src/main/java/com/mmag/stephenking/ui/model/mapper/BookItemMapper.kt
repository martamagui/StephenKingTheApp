package com.mmag.stephenking.ui.model.mapper

import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.ui.model.BookItem

fun Book.toUIModel(): BookItem =
    BookItem(
        id = this.id,
        publisher = this.publisher,
        title = this.title,
        year = this.year
    )