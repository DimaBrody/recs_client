package com.test.books.ui.store.discover.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import com.test.books.R
import com.test.books.data.model.book.BookServer
import com.test.books.databinding.ItemDiscoverBookBinding
import com.test.books.databinding.ItemSmallDiscoverBookBinding
import com.test.books.ui.base.BaseRecyclerViewAdapter
import com.test.books.ui.base.BaseViewHolder
import com.test.books.utils.views.TransactionPair

class DiscoverBooksSmallAdapter(
    books: List<BookServer> = mutableListOf(),
    private val picasso: Picasso,
    private val onItemClickListener: ((BookServer, Array<TransactionPair>) -> Unit)? = null
) : BaseRecyclerViewAdapter<BookServer, DiscoverBooksSmallAdapter.BooksViewHolder>() {

    init {
        updateList(books)
    }

    class BooksViewHolder(
        private val binding: ItemSmallDiscoverBookBinding,
        private val picasso: Picasso,
        private val onItemClickListener: ((BookServer, Array<TransactionPair>) -> Unit)?
    ) : BaseViewHolder<BookServer>(binding.root) {

        override fun bind(item: BookServer, position: Int) {
            binding.book = item
            binding.picasso = picasso
            binding.root.setOnClickListener {
//                DetailsActivity.startActivity(binding.itemBookTransformation, item)
//                onItemClickListener?.invoke(item, arrayOf(binding.itemBookCover.toTransitionName()))
            }
        }
    }


    override fun getViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder = BooksViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_small_discover_book, parent, false
        ), picasso, onItemClickListener
    )

    override fun areItemsTheSame(oldItem: BookServer, newItem: BookServer): Boolean = false

    override fun areContentTheSame(oldItem: BookServer, newItem: BookServer): Boolean = false

}