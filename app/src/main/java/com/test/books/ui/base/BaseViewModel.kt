package com.test.books.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun mainScope(callback: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(Dispatchers.Main) {
        callback()
    }

}