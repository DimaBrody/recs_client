package com.test.books.ui.store.discover.adapters

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.test.books.R
import com.test.books.data.model.book.BookServer
import com.test.books.data.model.book.BooksHolder
import com.test.books.databinding.ItemDiscoverContainerBookBinding
import com.test.books.ui.base.BaseAnimatedAdapter
import com.test.books.ui.base.BaseViewHolder
import com.test.books.ui.store.discover.more.MoreActivity
import com.test.books.utils.views.TransactionPair

class DiscoverServerAdapter(
    private val picasso: Picasso
) :
    BaseAnimatedAdapter<BooksHolder, DiscoverServerAdapter.DiscoverMainViewHolder>() {

    private var onItemClickListener: ((BookServer, Array<TransactionPair>) -> Unit)? = null


    inner class BooksContainerViewHolder(
        private val binding: ItemDiscoverContainerBookBinding
    ) : DiscoverMainViewHolder(binding.root) {
        override fun bind(item: BooksHolder, position: Int) {
            super.bind(position, item)
            binding.title = item.title
            binding.adapter = DiscoverBooksSmallAdapter(item.data, picasso, onItemClickListener)
            binding.arrow.setOnClickListener {
                if (item.type != null) {
                    val context = binding.root.context
                    context.startActivity(
                        Intent(context, MoreActivity::class.java).putExtra(
                            "type",
                            item.type
                        ).putExtra("title", item.title)
                    )
                }
            }
        }
    }

    abstract inner class DiscoverMainViewHolder(view: View) : BaseViewHolder<BooksHolder>(view) {
        open fun bind(position: Int, booksHolder: BooksHolder) {
            animateItem(itemView, position)
        }
    }

    fun setOnItemClickListener(onItemClickListener: (BookServer, Array<TransactionPair>) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }


    override fun getViewHolder(parent: ViewGroup, viewType: Int): DiscoverMainViewHolder =
        BooksContainerViewHolder(inflateBinding(parent, R.layout.item_discover_container_book))

    override fun areItemsTheSame(oldItem: BooksHolder, newItem: BooksHolder): Boolean =
        oldItem.title?.equals(newItem.title) == true

    override fun areContentTheSame(oldItem: BooksHolder, newItem: BooksHolder): Boolean = true
}