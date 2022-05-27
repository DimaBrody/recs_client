package com.test.books.data.model.book

import com.test.books.data.model.MockUtil
import com.test.books.data.model.result.ResultOf

data class BooksHolder(
    private val innerData: List<Book>? = null,
    val title: String? = null
) {

    val isInitial: Boolean
        get() = title == INITIAL_HOLDER_TITLE

    val data: List<Book>
        get() = innerData ?: listOf()

    companion object {
        private const val INITIAL_HOLDER_TITLE = "initial_books_holder_title"
        private const val FAILED_TO_LOAD_TITLE = "failed_to_load"

        fun createMock(amount: Int = 3, title: String? = null) =
            BooksHolder(MockUtil.createBooks(amount), title)

        fun createInitial() = BooksHolder(null, INITIAL_HOLDER_TITLE)


        fun transformResult(result: ResultOf<List<Book>>): BooksHolder =
            if (result is ResultOf.Success)
                BooksHolder(result.data, "For you")
            else BooksHolder(null, FAILED_TO_LOAD_TITLE)

    }
}