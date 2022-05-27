package com.test.books.binding

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import com.test.books.R
import com.test.books.data.model.MockUtil
import com.test.books.ui.details.DetailsBlurryListener
import com.test.books.utils.helpers.BlurryHelper
import com.test.books.ui.views.images.CrossfadeImageView

@BindingAdapter("blurryImageUrl", "blurryHelper", "blurryListener", requireAll = true)
fun bindImageViewBlur(imageView: CrossfadeImageView, imageUrl: String, blurryHelper: BlurryHelper, blurryListener: DetailsBlurryListener) {
    if (imageUrl == MockUtil.MOCK_COVER) {
        blurryHelper.put(imageView, blurryListener, R.drawable.adventure_books_large)
    }
}

@BindingAdapter("imageUrl")
fun bindImageViewUrl(imageView: ImageView, imageUrl: String) {
    val resources = imageView.resources
    if (imageUrl == MockUtil.MOCK_COVER) {
        imageView.setImageDrawable(
            BitmapFactory.decodeResource(
                resources, R.drawable.adventure_books_large
            ).toDrawable(resources)
        )
    }
}