package com.test.books.network.api

import com.test.books.data.model.response.RecommendationResponse
import com.test.books.data.model.response.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksService {

    @GET("/api/recommendations")
    suspend fun fetchData(
        @Query("type") type: String,
        @Query("count") count: Int = 10
    ): ServerResponse

    @GET("/api/user/books")
    suspend fun fetchUser() : ServerResponse

    companion object {
        const val USER_TYPE = "user"
        const val USER_USER = "base"
        const val CONTENT_BASED = "cb"
        const val RBM = "rbm"
        const val HYBRID = "hybrid"
        const val MATRIX = "matrix"
    }
}