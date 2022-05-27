package com.test.books.utils.functions

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.books.R
import com.test.books.data.model.GenreChip

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Number.px: Int
    get() = (this.toFloat() * Resources.getSystem().displayMetrics.density).toInt()

fun GenreChip.createTextView(context: Context) : TextView {
    val textView = TextView(ContextThemeWrapper(context, R.style.ChipTextView))
    textView.id = View.generateViewId()
    textView.text = name
    return textView
}

fun TextView.htmlText(text: CharSequence?){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        setText(Html.fromHtml(text.toString(), Html.FROM_HTML_MODE_COMPACT))
    } else {
         setText(Html.fromHtml(text.toString()))
    }
}