package com.test.books.ui.views.books

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.marginTop
import com.test.books.utils.functions.dp
import com.test.books.utils.functions.px

class DetailsBook @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val newHeight = (MeasureSpec.getSize(heightMeasureSpec) - BOOK_MARGIN_TOP.px) * BOOK_WIDTH_PERCENTAGE
//        val newWidth = newHeight * BOOK_RATIO
//
//        val widthNewSpec = MeasureSpec.makeMeasureSpec(newWidth.toInt(), MeasureSpec.EXACTLY)
//        val heightNewSpec = MeasureSpec.makeMeasureSpec(newHeight.toInt(), MeasureSpec.EXACTLY)
//
//        Log.d("DetailsBook", "${newWidth.toInt().dp}, ${newHeight.toInt().dp}")
//
//        super.onMeasure(widthNewSpec, heightNewSpec)
//    }

    companion object {
        private const val BOOK_RATIO = 0.7f
        private const val BOOK_WIDTH_PERCENTAGE = 0.8f
        private const val BOOK_MARGIN_TOP = 56
    }
}