package com.test.books.utils.functions

import android.util.Log
import com.test.books.data.model.book.BookUserReview
import com.test.books.ui.base.callbacks.RecyclerViewFilter

fun BookUserReview.isSearch(filter: RecyclerViewFilter<Int>): Boolean {
    val filterValue = filter.filterQuery()

    Log.d("ReviewsDialog5", filterValue.toString())

    return if (filterValue !in 1..5) true else this.rating == filterValue
}