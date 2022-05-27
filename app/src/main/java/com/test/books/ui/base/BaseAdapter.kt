package com.test.books.ui.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.books.data.model.book.BookUserReview
import com.test.books.ui.base.callbacks.DiffCallback

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder>(
     val items: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<VH>() {

    protected fun updateDifferentialList(newItems: List<T>) {
        val res = DiffUtil
            .calculateDiff(object : DiffCallback<T>(items.toList(), newItems) {
                override fun areItemsTheSame(
                    oldItem: T,
                    newItem: T
                ): Boolean {
                    return this@BaseAdapter.areItemsTheSame(oldItem, newItem)
                }

                override fun areContentsTheSame(
                    oldItem: T,
                    newItem: T
                ): Boolean {
                    return this@BaseAdapter.areContentsTheSame(oldItem, newItem)
                }
            })

        items.clear()
        items.addAll(newItems)
        res.dispatchUpdatesTo(this)
    }

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

}