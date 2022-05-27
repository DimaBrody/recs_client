package com.test.books.utils.functions

inline fun <T> List<T>.ifNotEmpty(crossinline callback : (List<T>) -> Unit){
    if(isNotEmpty()){
        callback(this)
    }
}