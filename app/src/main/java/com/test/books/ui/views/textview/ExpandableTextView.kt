package com.test.books.ui.views.textview

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.TextView.BufferType
import com.test.books.R
import com.test.books.utils.animations.EXPANDABLE_TEXT_DURATION
import com.test.books.utils.animations.fadeIn
import com.test.books.utils.animations.fadeOut
import com.test.books.utils.animations.fadeTo
import com.test.books.utils.functions.htmlText


class ExpandableTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), View.OnClickListener {

    private val DEFAULT_TRIM_LENGTH = 200
    private val ELLIPSIS = " <html><body><font color='#4880AB'>далее...</font></body></html>"

    private var originalText: CharSequence? = null
    private var isExpanded = false

    private var fullTextView: TextView
    private var shortTextView: TextView

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView)
            originalText = a.getText(R.styleable.ExpandableTextView_fullText)
            a.recycle()
        }

        setBackgroundColor(Color.TRANSPARENT)
        background = null

        fullTextView = TextView(ContextThemeWrapper(context, R.style.DescriptionTextView))
        fullTextView.visibility = View.GONE
        fullTextView.setText(originalText, BufferType.NORMAL)

        shortTextView = TextView(ContextThemeWrapper(context, R.style.DescriptionTextView))
        shortTextView.setOnClickListener(this)
        shortTextView.htmlText(getTrimmedText())

        addView(fullTextView)
        addView(shortTextView)
    }

    private fun getTrimmedText(): CharSequence? {
        return if (originalText != null && originalText!!.length > DEFAULT_TRIM_LENGTH) {
            SpannableStringBuilder(originalText, 0, DEFAULT_TRIM_LENGTH + 1).append(ELLIPSIS)
        } else {
            originalText
        }
    }

    override fun onClick(v: View?) {
        if(!isExpanded){
            isExpanded = true
            fullTextView.fadeIn(EXPANDABLE_TEXT_DURATION)
            shortTextView.fadeOut(EXPANDABLE_TEXT_DURATION/2)
        }
    }

}