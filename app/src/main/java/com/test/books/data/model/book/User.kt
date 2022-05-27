package com.test.books.data.model.book

data class User(
    val id: String,
    val name: String,
    val avatarUrl: String,
    val favoriteQuote: String,
    //TODO: list of favorites List<Int> or List<Favorite>
    val favorites: Int,
    //TODO: list of reviews
    val reviews: Int,
    //TODO: list of purchases
    val purchases: Int
)
