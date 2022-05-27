package com.test.books.utils.delegates

import androidx.fragment.app.Fragment
import com.test.books.navigation.NavigationController
import com.test.books.ui.base.BaseActivity
import java.lang.ref.WeakReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun navController() = object : ReadOnlyProperty<Fragment, NavigationController?> {

    private lateinit var navController: WeakReference<NavigationController>

    override fun getValue(thisRef: Fragment, property: KProperty<*>): NavigationController? {
        if (!::navController.isInitialized) {
            val baseActivity = (thisRef.activity as? BaseActivity)
            navController = WeakReference(baseActivity?.navController)
        }

        return navController.get()
    }

}