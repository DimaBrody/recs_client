package com.test.books.ui.views.images

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.test.books.R

open class OverlayImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    private var overlayColor = Color.TRANSPARENT
    private var overlayView: View
    var crossfade = 0f
        set(value) {
            field = value
            updateDrawableCrossfade()
        }

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.OverlayImageView)
//            crossfade = a.getFloat(R.styleable.OverlayImageView_crossfade, 0f)
            overlayColor = a.getColor(R.styleable.OverlayImageView_overlayColor, Color.TRANSPARENT)
            a.recycle()
        }

        overlayView = View(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(resources.getColor(R.color.black_transparent_50))
        }

        updateDrawableCrossfade()
    }

    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        updateDrawableCrossfade()
    }

    private fun updateDrawableCrossfade() {
        drawable?.alpha = (255f * crossfade).toInt()
        overlayView.alpha = crossfade
    }
}