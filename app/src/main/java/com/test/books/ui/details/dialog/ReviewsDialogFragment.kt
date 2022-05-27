package com.test.books.ui.details.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.chip.Chip
import com.test.books.R
import com.test.books.data.model.book.BookUserReview
import com.test.books.databinding.FragmentReviewBinding
import com.test.books.ui.base.BaseDialogFragment
import com.test.books.ui.base.callbacks.RecyclerViewFilter
import com.test.books.ui.details.adapter.DetailsReviewsAdapter

class ReviewsDialogFragment : BaseDialogFragment<FragmentReviewBinding>(R.layout.fragment_review),
    RecyclerViewFilter<Int> {

    private lateinit var items: MutableList<BookUserReview>
    private lateinit var adapter: DetailsReviewsAdapter

    private var selectedChipRating: Int = -1
        set(value) {
            field = value
            if (value >= 0) adapter.notifyFilterChanged()
        }

    override fun created() {
        super.created()
        arguments?.let {
            items = it.getParcelableArrayList<BookUserReview>(REVIEWS_ITEMS_KEY)
                ?.toMutableList() ?: mutableListOf()
        }
    }

    override fun bindData(binding: FragmentReviewBinding) {
        adapter = DetailsReviewsAdapter(items, true)
        adapter.filter = this
        adapter.updateList(items)

        binding.adapter = adapter
        binding.onBackListener = View.OnClickListener { dismiss() }

        binding.reviewCheckGroup.setOnCheckedChangeListener { view, checkedId ->
            selectedChipRating = when (checkedId) {
                R.id.review_chip_all -> 0
                else -> {
                    val clickedChip = view.findViewById<Chip>(checkedId)
                    clickedChip?.text?.toString()?.toInt() ?: -1
                }
            }
        }
    }

    override fun filterQuery(): Int = selectedChipRating

    companion object {
        const val REVIEWS_ITEMS_KEY = "items_key"

        fun show(fragmentManager: FragmentManager, items: MutableList<BookUserReview>) {
            val fragment = ReviewsDialogFragment().apply {
                arguments = Bundle()
                arguments?.putParcelableArrayList(REVIEWS_ITEMS_KEY, ArrayList(items))
            }
            fragment.show(fragmentManager, fragment::class.java.simpleName)
        }

    }

}