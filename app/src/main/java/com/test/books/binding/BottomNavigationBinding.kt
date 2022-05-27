package com.test.books.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.books.R
import com.test.books.ui.main.listeners.NavigationListener

@BindingAdapter("selectedItem")
fun bindSelectedItem(view: BottomNavigationView, id: Int) {
    view.menu.getItem(id).isChecked = true
}

@BindingAdapter("navListener")
fun bindNavListener(view: BottomNavigationView, listener: NavigationListener) {
    listener.storeListener.invoke()

    view.setOnNavigationItemSelectedListener {
        if (view.selectedItemId == it.itemId)
            return@setOnNavigationItemSelectedListener false

        when (it.itemId) {
            R.id.bottom_menu_library -> listener.libraryListener()
            R.id.bottom_menu_store -> listener.storeListener()
            R.id.bottom_menu_profile -> listener.profileListener()
        }
        true
    }
}