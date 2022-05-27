package com.test.books.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.books.utils.helpers.StartSnapHelper

@BindingAdapter("adapter", "isHorizontal", "isAutoMeasurement",  requireAll = false)
fun bindRecyclerAdapterHorizontal(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<*>,
    isHorizontal: Boolean? = null,
    isAutoMeasurement: Boolean? = null
) {
    recyclerView.adapter = adapter

    val layoutManager = if (isHorizontal == true)
        LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
    else object : LinearLayoutManager(recyclerView.context) {
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