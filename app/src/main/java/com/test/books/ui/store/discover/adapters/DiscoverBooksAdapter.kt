package com.test.books.ui.store.discover.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.books.R
import com.test.books.data.model.book.Book
import com.test.books.databinding.ItemDiscoverBookBinding
import com.test.books.ui.details.DetailsActivity
import com.test.books.utils.views.TransactionPair
import com.test.books.utils.views.toTransitionName

class DiscoverBooksAdapter(
    private var books: List<Book> = mutableListOf(),
    private val onItemClickListener: ((Book, Array<TransactionPair>) -> Unit)? = null
) : RecyclerView.Adapter<DiscoverBooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder =
        BooksViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_discover_book, parent, false
            ), onItemClickListener
        )

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    fun update(books: MutableList<Book>){
        this.books = books
        notifyDataSetChanged()
    }

    class BooksViewHolder(
        private val binding: ItemDiscoverBookBinding,
        private val onItemClickListener: ((Book, Array<TransactionPair>) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book
            binding.root.setOnClickListener {
                DetailsActivity.startActivity(binding.itemBookTransformation, book)
                onItemClickListener?.invoke(book, arrayOf(binding.itemBookCover.toTransitionName()))
            }
        }
    }

}