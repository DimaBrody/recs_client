package com.test.books.data.model.book

import com.test.books.data.model.response.RecommendationResponse
import com.test.books.data.model.result.ResultOf

data class BooksHolder(
    private val innerData: List<BookServer>? = null,
    val title: String? = null,
    val type: String? = null
) {

    val isInitial: Boolean
        get() = title == INITIAL_HOLDER_TITLE

    val data: List<BookServer>
        get() = innerData ?: listOf()

    companion object {
        private const val INITIAL_HOLDER_TITLE = "initial_books_holder_title"
        private const val FAILED_TO_LOAD_TITLE = "failed_to_load"

//        fun createMock(amount: Int = 3, title: String? = null) =
//            BooksHolder(MockUtil.createBooks(amount), title)

        fun createInitial() = BooksHolder(null, INITIAL_HOLDER_TITLE)


        fun transformResult(result: ResultOf<RecommendationResponse>, type: String? = null): BooksHolder =
            if (result is ResultOf.Success)
                BooksHolder(result.data.books, result.data.title, type)
            else BooksHolder(
                null,
                FAILED_TO_LOAD_TITLE
            ).also { (result as? ResultOf.Failure)?.t?.printStackTrace() }

    }
}