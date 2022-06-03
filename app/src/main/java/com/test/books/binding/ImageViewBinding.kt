package com.test.books.binding

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.test.books.R
import com.test.books.data.model.MockUtil
import com.test.books.ui.details.DetailsBlurryListener
import com.test.books.ui.views.images.CrossfadeImageView
import com.test.books.utils.helpers.BlurryHelper


@BindingAdapter("blurryImageUrl", "blurryHelper", "blurryListener", requireAll = true)
fun bindImageViewBlur(
    imageView: CrossfadeImageView,
    imageUrl: String,
    blurryHelper: BlurryHelper,
    blurryListener: DetailsBlurryListener
) {
    if (imageUrl == MockUtil.MOCK_COVER) {
        blurryHelper.put(imageView, blurryListener, R.drawable.adventure_books_large)
    }
}

const val SCALE_SIZE = 0.706f

val transformation = object : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val width = source.width
        val result = Bitmap.createScaledBitmap(source, width, (width / SCALE_SIZE).toInt(), false)
        if (result != source) {
            // Same bitmap is returned if sizes are the same
            source.recycle()
        }
        return result
    }

    override fun key(): String {
        return "transformation" + " desiredWidth"
    }
}

@BindingAdapter("imageUrl", "picasso", requireAll = false)
fun bindImageViewUrl(imageView: ImageView, imageUrl: String, picasso: Picasso? = null) {
    val resources = imageView.resources
    if (imageUrl == MockUtil.MOCK_COVER) {
        imageView.setImageDrawable(
            BitmapFactory.decodeResource(
                resources, R.drawable.adventure_books_large
            ).toDrawable(resources)
        )
    } else picasso?.load(imageUrl)?.transform(transformation)?.error(R.drawable.cover_placeholder)
        ?.placeholder(R.drawable.cover_placeholder)?.into(imageView)
}

