package com.test.books.data.model

import com.test.books.data.model.DiscoverItemHolder.Companion.BOOKS
import com.test.books.data.model.DiscoverItemHolder.Companion.STRIPE
import com.test.books.data.model.book.*
import com.test.books.utils.functions.random
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt

object MockUtil {

    const val MOCK_COVER = "mock_cover"

    fun createBooks(amount: Int = 3): MutableList<Book> {
        val items = mutableListOf<Book>()

        for (i in 0 until amount) {
            items.add(
                Book(
                    "book$i${random(100000)}",
                    "Война и Мир",
                    "Лев Толстой",
                    78.5,
                    5.0,
                    "«Интересная цитата с книги»",
                    MOCK_COVER,
                    BookDetails("2020", "321")
                )
            )
        }
        return items
    }

    fun createGenres(amount: Int = 5): MutableList<GenreChip> {
        val items = mutableListOf<GenreChip>()

        for (i in 0 until amount) {
            items.add(
                GenreChip("Chip ${(random(10000))}", "chip_genre_id_$i")
            )
        }
        return items
    }

    fun createDiscoverItems(): MutableList<DiscoverItemHolder> {
        return mutableListOf(
            DiscoverItemHolder(STRIPE, 3),
            DiscoverItemHolder(BOOKS, 10)
        )
    }

    fun createBookReviews(): BookReviews {
        val reviewsMap = hashMapOf<Int, Int>()

        for (i in 1..5) {
            reviewsMap[i] = random(1000 * i)
        }

        return BookReviews(reviewsMap)
    }

    fun createUserReviews(amount: Int = 5): MutableList<BookUserReview> {
        val items = mutableListOf<BookUserReview>()

        for (i in 0 until amount) {
            items.add(
                BookUserReview(
                    "$i${random(1000) + 1000}",
                    "",
                    "Brody",
                    Date(random((System.currentTimeMillis() / 1000).toInt()).toLong() * 1000),
                    random(4) + 1,
                    "qweasd",
                    random(100),
                    random(20)
                )
            )
        }

        return items
    }

    fun createUser(name: String = "Brody") = User("1", name, "", "Amaterasu", 12, 24, 3)
}