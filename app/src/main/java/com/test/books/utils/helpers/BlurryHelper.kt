package com.test.books.utils.helpers

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toDrawable
import com.test.books.ui.details.DetailsBlurryListener
import com.test.books.ui.views.images.CrossfadeImageView
import com.test.books.utils.delegates.weak
import jp.wasabeef.blurry.Blurry

class BlurryHelper(context: Context) {

    private val defaultComposer = Blurry.with(context)

    private val context by weak(context.applicationContext)

    private val defaultEditTransform: Blurry.Composer.() -> Unit = {
        radius(DEFAULT_RADIUS)
        sampling(DEFAULT_SAMPLING)
    }

    fun put(
        imageView: CrossfadeImageView,
        blurryListener: DetailsBlurryListener,
        @DrawableRes resId: Int,
        editComposer: Blurry.Composer.() -> Unit = defaultEditTransform
    ) {
        putImpl(imageView, blurryListener, resId = resId, editComposer = editComposer)
    }

    fun put(
        imageView: CrossfadeImageView,
        blurryListener: DetailsBlurryListener,
        drawable: Drawable,
        editComposer: Blurry.Composer.() -> Unit = defaultEditTransform
    ) {
        putImpl(imageView, blurryListener, drawable = drawable, editComposer = editComposer)
    }

    private fun putImpl(
        imageView: CrossfadeImageView,
        blurryListener: DetailsBlurryListener,
        @DrawableRes resId: Int? = null,
        drawable: Drawable? = null,
        editComposer: Blurry.Composer.() -> Unit = defaultEditTransform
    ) = context?.let { context ->
        editComposer.invoke(defaultComposer)

        imageView.post {
            defaultComposer.capture(imageView).getAsync {
                if (it != null && !it.isRecycled) {
                    imageView.crossfade = 1f
                    blurryListener.onBlurredImageCreated()
                    imageView.setAltDrawable(it.toDrawable(context.resources))
                }
            }
        }
    }

    companion object {
        const val DEFAULT_RADIUS = 20
        const val DEFAULT_SAMPLING = 10
    }


}