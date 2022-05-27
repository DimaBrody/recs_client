package com.test.books.ui.store.discover.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.books.R
import com.test.books.data.model.book.Book
import com.test.books.databinding.ItemDiscoverStripeBinding
import com.test.books.ui.details.DetailsActivity
import com.test.books.utils.helpers.BlurryHelper
import com.test.books.utils.animations.DETAILS_ALPHA
import com.test.books.utils.animations.fadeTo
import com.test.books.utils.segregation.onDetailsBlurryListener
import com.test.books.utils.views.TransactionPair
import com.test.books.utils.views.toTransitionName

class DiscoverStripeAdapter(
    private val blurryHelper: BlurryHelper,
    private var books: List<Book> = mutableListOf(),
    private val onItemClickListener: ((Book, Array<TransactionPair>) -> Unit)? = null,
) : RecyclerView.Adapter<DiscoverStripeAdapter.StripeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StripeViewHolder =
        StripeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_discover_stripe,
                parent,
                false
            ), blurryHelper, onItemClickListener
        )

    override fun onBindViewHolder(holder: StripeViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    fun update(books: MutableList<Book>) {
        this.books = books
        notifyDataSetChanged()
    }


    class StripeViewHolder(
        private val binding: ItemDiscoverStripeBinding,
        private val blurryHelper: BlurryHelper,
        private val onItemClickListener: ((Book, Array<TransactionPair>) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book
            binding.blurryHelper = blurryHelper
            binding.blurryListener = onDetailsBlurryListener {
                binding.itemStripeOverlay.fadeTo(DETAILS_ALPHA)
            }
            binding.root.setOnClickListener {
                DetailsActivity.startActivity(binding.itemStripeTransformation, book)
                onItemClickListener?.invoke(
                    book, arrayOf(binding.itemStripeImageBook.toTransitionName())
                )
            }
        }
    }
}