package com.test.books.utils.functions

import kotlin.math.roundToInt

fun random(random: Int) = (Math.random() * random).roundToInt()

fun Float.oneSymbolPrecision(): Float = ((this * 10).roundToInt().toDouble() / 10).toFloat()