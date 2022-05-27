package com.test.books.utils.functions

import java.lang.IllegalArgumentException

fun throwIllegal(message: String = "Wrong arguments."): Nothing =
    throw IllegalArgumentException(message)