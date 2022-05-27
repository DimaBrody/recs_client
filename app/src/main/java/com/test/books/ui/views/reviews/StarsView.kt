package com.test.books.ui.views.reviews

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.test.books.R
import com.test.books.utils.functions.oneSymbolPrecision
import com.test.books.utils.functions.px
import com.test.books.utils.views.RECT_RIGHT
import com.test.books.utils.views.changeRectValue
import kotlin.math.floor
import kotlin.math.roundToInt


class StarsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    companion object {
        const val STAR_SIZE = 12
        const val STAR_AMOUNT = 5
    }

    private val mainColor = ContextCompat.getColor(context, R.color.gradient_start_main_color)

    private val paint = Paint().apply {
        colorFilter = PorterDuffColorFilter(mainColor, PorterDuff.Mode.SRC_IN)
    }

    private val starSizeInPx = STAR_SIZE.px

    private val backgroundBitmap =
        drawableToBitmap(
            ContextCompat.getDrawable(context, R.drawable.ic_star),
            starSizeInPx,
            starSizeInPx
        )

    private val bitmapSource = Rect().apply {
        top = 0
        left = 0

        if (backgroundBitmap != null) {
            right = starSizeInPx
            bottom = starSizeInPx
        }
    }

    private val bitmapDest = Rect(bitmapSource)

    var rating: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        if (rating != 0f) {
            canvas?.let {
                if (backgroundBitmap != null) {
                    paint.alpha = (255 * .3f).toInt()
                    it.save()

                    for (i in 0 until STAR_AMOUNT) {
                        it.drawBitmap(backgroundBitmap, bitmapSource, bitmapDest, paint)
                        it.translate(height.toFloat(), 0f)
                    }

                    it.restore()

                    paint.alpha = 255

                    val floorRating = floor(rating.toDouble()).toInt()
                    val ratingDifference = rating - floorRating
                    val ratingRemainder = .15f + (ratingDifference * .7f)

                    for (i in 0 until floorRating) {
                        it.drawBitmap(backgroundBitmap, bitmapSource, bitmapDest, paint)
                        it.translate(height.toFloat(), 0f)
                    }

                    if (ratingDifference > .05) {
                        val changedSize = bitmapDest.width() * ratingRemainder

                        changeRectValue(changedSize, RECT_RIGHT, bitmapDest, bitmapSource)
                        it.drawBitmap(backgroundBitmap, bitmapSource, bitmapDest, paint)
                        changeRectValue(starSizeInPx, RECT_RIGHT, bitmapDest, bitmapSource)
                    }
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = starSizeInPx
        val width = size * STAR_AMOUNT

        setMeasuredDimension(width, size)
    }

    private fun validateRating(receivedRating: Float): Float = when {
        receivedRating < 0 -> 0f
        receivedRating > 5.0 -> 5.0f
        else -> receivedRating.oneSymbolPrecision()
    }


    private fun drawableToBitmap(
        nullableDrawable: Drawable?,
        width: Number? = null,
        height: Number? = null
    ): Bitmap? =
        nullableDrawable?.let { drawable ->
            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }
            val bitmap =
                Bitmap.createBitmap(
                    width?.toInt() ?: drawable.intrinsicWidth,
                    height?.toInt() ?: drawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }

}