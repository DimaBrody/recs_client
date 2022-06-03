package com.test.books.utils.functions

import android.util.Log
import kotlin.reflect.KProperty

fun Any?.log(message: Any?) =
    Log.d(this?.let { it::class.java.simpleName }.toString(), message.toString())

fun log(message: Any?) = Log.d("DefaultTag", message.toString())