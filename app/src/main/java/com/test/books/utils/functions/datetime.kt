package com.test.books.utils.functions

import android.content.Context
import com.test.books.R
import java.util.*

fun Calendar.getMonthFromCalendar(context: Context): String =
    context.resources.getStringArray(R.array.month_names)[get(Calendar.MONTH)]