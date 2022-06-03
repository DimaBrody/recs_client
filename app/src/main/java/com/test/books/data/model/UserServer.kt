package com.test.books.data.model

import com.test.books.data.model.book.BookServer

data class UserServer(
    var user: Int? = null,
    var books: List<BookServer>? = null
)