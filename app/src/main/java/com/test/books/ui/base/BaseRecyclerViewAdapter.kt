package com.test.books.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.books.ui.base.callbacks.DiffCallback
import com.test.books.utils.aliases.OnItemClickListener

abstract class BaseRecyclerViewAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {

    protected var items = mutableListOf<T>()

    private val itemClickListeners = mutableListOf<OnItemClickListener>()
    private var comparator: Comparator<T>? = null

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): VH

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentTheSame(oldItem: T, newItem: T): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val viewHolder = getViewHolder(parent, viewType)
        viewHolder.setClickListeners(itemClickListeners)
        return viewHolder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun getItemAt(position: Int) =
        if (position !in 0 until items.size) null else items[position]

    fun removeItem(item: T) {
        items.remove(item)
        notifyItemRemoved(items.size - 1)
    }

    fun addList(newItems: List<T>) {
        updateList(this.items + newItems)
    }

    open fun updateList(newItems: List<T>) {
        comparator?.let { newItems.sortedWith(it) }

        val diffUtil = DiffUtil.calculateDiff(object : DiffCallback<T>(items, newItems) {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                return this@BaseRecyclerViewAdapter.areItemsTheSame(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                return this@BaseRecyclerViewAdapter.areContentTheSame(oldItem, newItem)
            }
        })

        items.clear()
        items.addAll(newItems)

        diffUtil.dispatchUpdatesTo(this)

        onListUpdated()
    }

    open fun onListUpdated() {}

    override fun getItemViewType(position: Int): Int = position

    protected fun <V : ViewDataBinding> inflateBinding(
        viewGroup: ViewGroup,
        layoutId: Int
    ): V {
        val inflater = LayoutInflater.from(viewGroup.context)
        return DataBindingUtil.inflate(inflater, layoutId, viewGroup, false)
    }

    protected fun inflate(
        viewGroup: ViewGroup,
        layoutId: Int
    ): View = LayoutInflater.from(viewGroup.context).inflate(layoutId, viewGroup, false)

    fun addItemClickListener(listener: OnItemClickListener) =
        itemClickListeners.add(listener)

    fun removeItemClickListener(listener: OnItemClickListener) =
        itemClickListeners.remove(listener)
}