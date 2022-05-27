package com.test.books.ui.store.discover.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.test.books.R
import com.test.books.data.model.book.BooksHolder
import com.test.books.data.model.DiscoverItemHolder
import com.test.books.data.model.MockUtil
import com.test.books.data.model.book.Book
import com.test.books.databinding.ItemDiscoverContainerBookBinding
import com.test.books.databinding.ItemDiscoverContainerStripeBinding
import com.test.books.ui.base.BaseAnimatedAdapter
import com.test.books.utils.helpers.BlurryHelper
import com.test.books.utils.functions.throwIllegal
import com.test.books.utils.views.TransactionPair

class DiscoverMainAdapter(
    private val blurryHelper: BlurryHelper,
    private val items: MutableList<DiscoverItemHolder> = MockUtil.createDiscoverItems()
) : BaseAnimatedAdapter<DiscoverMainAdapter.DiscoverMainViewHolder>() {

    private var onItemClickListener: ((Book, Array<TransactionPair>) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverMainViewHolder {
        return when (viewType) {
            DiscoverItemHolder.STRIPE -> StripeContainerViewHolder(
                provideBinding(parent, viewType),
                blurryHelper
            )
            DiscoverItemHolder.BOOKS -> BooksContainerViewHolder(provideBinding(parent, viewType))
            else -> throwIllegal()
        }
    }

    private fun <V : ViewDataBinding> provideBinding(parent: ViewGroup, viewType: Int): V {
        val inflater = LayoutInflater.from(parent.context)

        return DataBindingUtil.inflate(
            inflater, when (viewType) {
                DiscoverItemHolder.STRIPE -> R.layout.item_discover_container_stripe
                DiscoverItemHolder.BOOKS -> R.layout.item_discover_container_book
                else -> throwIllegal()
            }, parent, false
        )
    }

    override fun onBindViewHolder(holder: DiscoverMainViewHolder, position: Int) {
        holder.bind(position, BooksHolder.createMock(items[position].amount, "Подборка для вас"))
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].itemType
    }

    override fun getItemCount(): Int = items.size

    fun setOnItemClickListener(onItemClickListener: (Book, Array<TransactionPair>) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }


    inner class StripeContainerViewHolder(
        private val binding: ItemDiscoverContainerStripeBinding,
        private val blurryHelper: BlurryHelper
    ) : DiscoverMainViewHolder(binding.root) {
        override fun bind(position: Int, booksHolder: BooksHolder) {
            super.bind(position, booksHolder)
            binding.adapter =
                DiscoverStripeAdapter(blurryHelper, booksHolder.data, onItemClickListener)
        }
    }

    inner class BooksContainerViewHolder(
        private val binding: ItemDiscoverContainerBookBinding
    ) : DiscoverMainViewHolder(binding.root) {
        override fun bind(position: Int, booksHolder: BooksHolder) {
            super.bind(position, booksHolder)
            binding.title = booksHolder.title
            binding.adapter = DiscoverBooksAdapter(booksHolder.data, onItemClickListener)
        }
    }


    abstract inner class DiscoverMainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(position: Int, booksHolder: BooksHolder){
            animateItem(itemView, position)
        }
    }

}