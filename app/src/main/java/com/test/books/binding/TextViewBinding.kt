package com.test.books.binding

import android.graphics.Typeface
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.test.books.utils.functions.getMonthFromCalendar
import com.test.books.utils.functions.htmlText
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("fontFromAssets")
fun bindTextViewAssetsFont(view: TextView, path: String) {
    view.typeface = Typeface.createFromAsset(view.context.applicationContext.assets, "fonts/$path")
}

@BindingAdapter("htmlText")
fun bindTextViewHtml(view: TextView, text: String) {
    view.htmlText(text)
}

@BindingAdapter("dateText")
fun bindDateText(view: TextView, date: Date) {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = date.time
    }

    val minutes = calendar.get(Calendar.MINUTE)

    val stringDate = "${calendar.get(Calendar.DAY_OF_MONTH)} ${
        calendar.getMonthFromCalendar(view.context)
    } ${
        calendar.get(Calendar.YEAR)
    }, ${calendar.get(Calendar.HOUR_OF_DAY)}:${
        if (minutes > 9) "" else "0"
    }${calendar.get(Calendar.MINUTE)}"

    view.text = stringDate
}