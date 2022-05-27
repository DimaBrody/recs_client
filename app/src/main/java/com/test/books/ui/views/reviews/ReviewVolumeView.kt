package com.test.books.ui.views.reviews

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.test.books.R
import com.test.books.utils.functions.px

class ReviewVolumeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    companion object {
        const val PROGRESS_LEFT_MARGIN = 5f
        const val PROGRESS_TOP_MARGIN = 0.5f
        const val PROGRESS_HEIGHT = 8f
    }

    var progress: Int = 0
        set(value){
            field = value
            setupTextView()
            setupProgressBar()
        }
    
    private var text: String = ""

    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.ReviewVolumeView)

            text = ta.getString(R.styleable.ReviewVolumeView_volumeScore).toString()

            ta.recycle()
        }

        orientation = HORIZONTAL
    }

    private fun setupTextView() {
        val textView = TextView(ContextThemeWrapper(context, R.style.RatingTextView))
        textView.text = text
        addView(textView)
    }

    private fun setupProgressBar() {
        val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)

        progressBar.apply {
            this.progress = this@ReviewVolumeView.progress
            this.layoutParams = LayoutParams(MATCH_PARENT, PROGRESS_HEIGHT.px).also { lp ->
                lp.leftMargin = PROGRESS_LEFT_MARGIN.px
                lp.topMargin = PROGRESS_TOP_MARGIN.px
                lp.gravity = Gravity.CENTER
            }
            this.progressDrawable =
                ContextCompat.getDrawable(context, R.drawable.reviews_progress)
        }

        addView(progressBar)
    }

}