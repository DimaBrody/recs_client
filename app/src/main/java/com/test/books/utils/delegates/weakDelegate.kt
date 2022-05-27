package com.test.books.utils.delegates

import java.lang.ref.WeakReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T> weak(referencedItem: T?) = object : ReadOnlyProperty<Any?, T?> {

    private val reference = WeakReference(referencedItem)

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return reference.get()
    }
}
