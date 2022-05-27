package com.test.books.utils.functions

inline fun checkNullable(vararg items: Any?, crossinline onSuccess: () -> Unit){
    if(items.all { it != null }) onSuccess()
}

