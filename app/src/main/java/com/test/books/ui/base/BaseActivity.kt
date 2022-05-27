package com.test.books.ui.base

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.test.books.R
import com.test.books.navigation.NavigationController
import com.test.books.ui.main.MainActivity
import com.test.books.utils.ui.setDefaultStatusBar
import com.test.books.utils.ui.setStatusBarGradient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navController: NavigationController

    protected inline fun <reified V : ViewDataBinding> binding(@LayoutRes resId: Int): Lazy<V> =
        lazy { DataBindingUtil.setContentView(this, resId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}