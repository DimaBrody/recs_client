package com.test.books.data.model.response

data class ServerResponse(
    var status: String? = null,
    var content: RecommendationResponse? = null
)