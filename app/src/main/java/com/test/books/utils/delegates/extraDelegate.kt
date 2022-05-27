package com.test.books.utils.delegates

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

fun <T> Activity.lazyExtra(onCreateExtra: Intent.() -> T): Lazy<T> = lazy { onCreateExtra(intent) }