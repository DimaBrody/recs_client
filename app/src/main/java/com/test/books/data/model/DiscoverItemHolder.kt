package com.test.books.data.model

data class DiscoverItemHolder(
    val itemType: Int = STRIPE,
    val amount: Int = 3
) {
    companion object {
        const val STRIPE = 0x0
        const val BOOKS = 0x1
    }
}