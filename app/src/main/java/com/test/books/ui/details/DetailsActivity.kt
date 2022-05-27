package com.test.books.ui.details

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.doOnPreDraw
import com.google.android.material.appbar.AppBarLayout
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.test.books.R
import com.test.books.data.model.MockUtil
import com.test.books.data.model.book.Book
import com.test.books.databinding.ActivityDetailsBinding
import com.test.books.ui.base.BaseActivity
import com.test.books.ui.details.adapter.DetailsGenresAdapter
import com.test.books.ui.details.adapter.DetailsReviewsAdapter
import com.test.books.ui.details.dialog.ReviewsDialogFragment
import com.test.books.utils.helpers.BlurryHelper
import com.test.books.utils.animations.DETAILS_ALPHA
import com.test.books.utils.animations.fadeTo
import com.test.books.utils.delegates.lazyExtra
import com.test.books.utils.segregation.onDetailsBlurryListener
import com.test.books.utils.ui.getStatusBarHeight
import com.test.books.utils.ui.setWindowTranslucent
import com.test.books.utils.ui.setupEndTransformation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    @Inject
    lateinit var blurryHelper: BlurryHelper

    private val binding: ActivityDetailsBinding by binding(R.layout.activity_details)
    private val book: Book? by lazyExtra { getParcelableExtra(BOOK_EXTRA) }

    private lateinit var blurryListener: DetailsBlurryListener

    private lateinit var adapter: DetailsReviewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupEndTransformation()
        super.onCreate(savedInstanceState)
        setWindowTranslucent(this, true)

        val inflater = TransitionInflater.from(this)
        window.enterTransition = inflater.inflateTransition(R.transition.details_transition_in)

        val reviews = MockUtil.createUserReviews()
        adapter = DetailsReviewsAdapter(reviews)

        postponeEnterTransition()

        setupMotionLayouts()
        setupMotionViews()
        setupBlurryListener()

        binding.book = book
        binding.blurryHelper = blurryHelper
        binding.blurryListener = blurryListener
        binding.detailsHandler = DetailsBindingHandler()
        binding.chipAdapter = DetailsGenresAdapter(MockUtil.createGenres())
        binding.bookReviews = MockUtil.createBookReviews()
        binding.reviewsAdapter = adapter
        binding.detailsMotionBookImage.transitionName = book?.id

        startPostponedEnterTransition()
    }

    private fun setupMotionLayouts() = binding.apply {
        val statusBarHeight = getStatusBarHeight(applicationContext)
        val appBarHeight = statusBarHeight + resources
            .getDimensionPixelSize(R.dimen.detailsAppbarHeight)
        val appBarHeightCollapsed = statusBarHeight + resources
            .getDimensionPixelSize(R.dimen.actionBarSize)

        detailsAppbar.layoutParams.height = appBarHeight
        detailsAppbarFrame.minimumHeight = appBarHeightCollapsed
        detailsMotionLayout.layoutParams.let {
            if (it is ViewGroup.MarginLayoutParams)
                it.topMargin = statusBarHeight
        }
        detailsMotionLayoutToolbar.layoutParams.let {
            if (it is ViewGroup.MarginLayoutParams)
                it.topMargin = statusBarHeight
        }
    }

    private fun setupMotionViews() = binding.apply {
        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / detailsAppbar.totalScrollRange.toFloat()
            detailsMotionLayout.progress = seekPosition
            detailsMotionLayoutToolbar.progress = seekPosition
        }
        detailsAppbar.addOnOffsetChangedListener(listener)

        detailsMotionBook.apply {
            doOnPreDraw {
                pivotX = it.width.toFloat() / 2f
                pivotY = it.height.toFloat() / 2f
            }
        }
    }

    private fun setupBlurryListener() {
        blurryListener = onDetailsBlurryListener {
            binding.detailsOverlay.fadeTo(DETAILS_ALPHA)
        }
    }


    inner class DetailsBindingHandler {
        fun onReviewsClicked(view: View) {
            ReviewsDialogFragment.show(supportFragmentManager, adapter.items)
        }

        fun onBackClicked(view: View) {
            onBackPressed()
        }
    }

    companion object {

        const val BOOK_EXTRA = "book_extra"

        fun startActivity(transformationLayout: TransformationLayout, book: Book) {
            val context = transformationLayout.context
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(BOOK_EXTRA, book)
            TransformationCompat.startActivity(transformationLayout, intent)
        }
    }

}