package com.test.books.binding

import androidx.databinding.BindingAdapter
import com.test.books.ui.views.reviews.ReviewVolumeView
import com.test.books.ui.views.reviews.StarsView

@BindingAdapter("volumeProgress")
fun bindReviewVolume(view: ReviewVolumeView, progress: Int) {
    view.progress = progress
}

@BindingAdapter("rating")
fun bindReviewVolume(view: StarsView, rating: Number) {
    view.rating = rating.toFloat()
}
