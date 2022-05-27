package com.test.books.utils.segregation

import androidx.recyclerview.widget.RecyclerView
import com.test.books.ui.details.DetailsBlurryListener

inline fun onDetailsBlurryListener(crossinline onBlurry: () -> Unit) =
    object : DetailsBlurryListener {
        override fun onBlurredImageCreated() {
            onBlurry.invoke()
        }
    }


fun RecyclerView.setOnScrollStateChangedListener(onStateChanged: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            onStateChanged()
        }
    })
}
