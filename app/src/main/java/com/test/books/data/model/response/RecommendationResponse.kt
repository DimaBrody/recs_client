package com.test.books.data.model.response

import com.test.books.data.model.book.BookServer

data class RecommendationResponse(
    var title: String? = null,
    var books: List<BookServer>? = null
)