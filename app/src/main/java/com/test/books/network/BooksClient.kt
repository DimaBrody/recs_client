package com.test.books.network

import com.test.books.data.model.response.RecommendationResponse
import com.test.books.network.api.BooksService
import javax.inject.Inject

class BooksClient @Inject constructor(
    private val booksService: BooksService
) {

    suspend fun fetchData(type: String, count: Int = 100): RecommendationResponse {
        return booksService.fetchData(type, count).content!!
    }

    suspend fun fetchUser(): RecommendationResponse {
        return booksService.fetchUser().content!!
    }

}