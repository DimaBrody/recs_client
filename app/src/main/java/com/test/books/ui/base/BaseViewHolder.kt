package com.test.books.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.books.utils.aliases.OnItemClickListener

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    private var clickListeners: List<OnItemClickListener>? = null

    abstract fun bind(item: T, position: Int)

    init {
        itemView.setOnClickListener {
            clickListeners?.forEach {
                it.invoke(adapterPosition)
            }
        }
    }

    fun setClickListeners(clickListeners: List<OnItemClickListener>) {
        this.clickListeners = clickListeners
    }
}