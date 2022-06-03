package com.test.books.data.repository

import com.test.books.data.model.MockUtil
import com.test.books.data.model.book.Book
import com.test.books.data.model.response.RecommendationResponse
import com.test.books.data.model.result.ResultOf
import com.test.books.network.BooksClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val booksClient: BooksClient
) {

    fun getBooksDefault(): Flow<ResultOf<List<Book>>> = ResultOf.createFlowDefault<List<Book>>(
        onDebug = { MockUtil.createBooks(10) },
        onLoaded = { MockUtil.createBooks(10) },
        onDatabase = { MockUtil.createBooks(10) }
    )

    fun getData(type: String, count: Int = 10): Flow<ResultOf<RecommendationResponse>> = ResultOf.createFlow {
        booksClient.fetchData(type, count)
    }

    fun getUser(): Flow<ResultOf<RecommendationResponse>> = ResultOf.createFlow {
        booksClient.fetchUser()
    }


}