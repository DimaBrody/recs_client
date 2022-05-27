package com.test.books.utils.views

import android.graphics.Rect

const val RECT_LEFT = 0x1
const val RECT_TOP = 0x2
const val RECT_RIGHT = 0x3
const val RECT_BOTTOM = 0x4

fun changeRectValue(size: Number, side: Int, vararg rect: Rect) {
    val normalSize = size.toInt()
    rect.forEach {
        when (side) {
            RECT_LEFT -> it.left = normalSize
            RECT_RIGHT -> it.right = normalSize
            RECT_TOP -> it.top = normalSize
            RECT_BOTTOM -> it.bottom = normalSize
        }
    }
}
