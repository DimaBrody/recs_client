package com.test.books.ui.main.listeners

data class NavigationListener(
    val libraryListener: () -> Unit,
    val storeListener: () -> Unit,
    val profileListener: () -> Unit
)