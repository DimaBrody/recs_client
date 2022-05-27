package com.test.books.data.repository

import com.test.books.App
import com.test.books.data.model.MockUtil
import com.test.books.data.model.book.Book
import com.test.books.data.model.result.ResultOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository {

    fun getBooks(): Flow<ResultOf<List<Book>>> = ResultOf.createFlow<List<Book>>(
        onDebug = { MockUtil.createBooks(10) },
        onLoaded = { MockUtil.createBooks(10) },
        onDatabase = { MockUtil.createBooks(10) }
    )


}