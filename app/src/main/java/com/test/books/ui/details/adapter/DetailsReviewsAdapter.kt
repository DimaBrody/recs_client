package com.test.books.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.books.R
import com.test.books.data.model.book.BookUserReview
import com.test.books.databinding.ItemDetailsReviewBinding
import com.test.books.ui.base.BaseAdapter
import com.test.books.ui.base.callbacks.RecyclerViewFilter
import com.test.books.utils.functions.isSearch
import kotlin.collections.ArrayList
import kotlin.math.min

class DetailsReviewsAdapter(
    items: MutableList<BookUserReview> = mutableListOf(),
    private val isMoreReviews: Boolean = false
) : BaseAdapter<BookUserReview, DetailsReviewsAdapter.ReviewsViewHolder>(items) {

    private var unfilteredList: List<BookUserReview> = items
    var filter: RecyclerViewFilter<Int>? = null

    fun updateList(newItems: List<BookUserReview>) {
        unfilteredList = ArrayList(newItems)
        if (filter != null) {
            val filteredList: MutableList<BookUserReview> = ArrayList<BookUserReview>()
            for (item in newItems) {
                if (item.isSearch(filter!!)) {
                    filteredList.add(item)
                }
            }
            super.updateDifferentialList(filteredList)
        } else {
            super.updateDifferentialList(newItems)
        }
    }

    fun notifyFilterChanged() {
        updateList(unfilteredList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder =
        ReviewsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_details_review,
                parent, false
            )
        )

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = if (isMoreReviews) items.size else min(items.size, 3)

    override fun areItemsTheSame(oldItem: BookUserReview, newItem: BookUserReview): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BookUserReview, newItem: BookUserReview): Boolean =
        oldItem.date == newItem.date

    class ReviewsViewHolder(private val binding: ItemDetailsReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: BookUserReview) {
            binding.review = review
        }
    }

}