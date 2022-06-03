package com.test.books.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.books.data.model.book.BookServer
import com.test.books.data.model.book.BooksHolder
import com.test.books.ui.store.discover.adapters.DiscoverBooksSmallAdapter
import com.test.books.ui.store.discover.adapters.DiscoverServerAdapter
import com.test.books.utils.functions.log
import com.test.books.utils.helpers.StartSnapHelper

@BindingAdapter(
    "adapter",
    "isHorizontal",
    "isAutoMeasurement",
    "adapterGridCount",
    requireAll = false
)
fun bindRecyclerAdapterHorizontal(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<*>,
    isHorizontal: Boolean? = null,
    isAutoMeasurement: Boolean? = null,
    adapterGridCount: Int? = null
) {
    recyclerView.adapter = adapter

    val layoutManager = if (isHorizontal == true)
        LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
    else if (adapterGridCount != null) {
        object : GridLayoutManager(recyclerView.context, adapterGridCount) {
            override fun isAutoMeasureEnabled(): Boolean {
                return isAutoMeasurement ?: super.isAutoMeasureEnabled()
            }

            override fun canScrollVertically(): Boolean {
                return isAutoMeasurement ?: super.canScrollVertically()
            }
        }
    } else object : LinearLayoutManager(recyclerView.context) {
        override fun isAutoMeasureEnabled(): Boolean {
            return isAutoMeasurement ?: super.isAutoMeasureEnabled()
        }

        override fun canScrollVertically(): Boolean {
            return isAutoMeasurement ?: super.canScrollVertically()
        }
    }


    recyclerView.layoutManager = layoutManager

    if (isHorizontal == true) {
        recyclerView.onFlingListener = null
        val startSnapHelper = StartSnapHelper.create()

        startSnapHelper.attachToRecyclerView(recyclerView)
    }
}

@BindingAdapter("books")
fun bindBooksData(
    recyclerView: RecyclerView,
    books: List<BooksHolder>?
) {
    val adapter = recyclerView.adapter
    if (adapter is DiscoverServerAdapter) {
        books?.let {
            adapter.updateList(it)
        }
    }
}

@BindingAdapter("books_more")
fun bindBooksMore(
    recyclerView: RecyclerView,
    books: List<BookServer>?
) {
    val adapter = recyclerView.adapter
    if (adapter is DiscoverBooksSmallAdapter) {
        books?.let {
            adapter.updateList(it)
        }
    }
}