package com.test.books.utils.ui

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.skydoves.transformationlayout.onTransformationEndContainer
import com.test.books.R


fun setDefaultStatusBar(
    activity: Activity,
    @ColorRes color: Int = R.color.gradient_start_main_color
) {
    activity.window.apply {
        val background = ColorDrawable(ContextCompat.getColor(activity, R.color.white))
//        clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//
//        statusBarColor = ContextCompat.getColor(activity, color)
        setBackgroundDrawable(background)
    }
}

fun setStatusBarGradient(activity: Activity) {
    activity.window.apply {
        val background = ContextCompat.getDrawable(activity, R.drawable.main_theme_gradient)

        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = ContextCompat.getColor(activity, R.color.transparent)
        setBackgroundDrawable(background)
    }
}

fun setWindowTranslucent(activity: Activity, on: Boolean) {
    val bits =
        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS and WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS.inv()
    val win: Window = activity.window
    val winParams: WindowManager.LayoutParams = win.attributes
    if (on) {
        winParams.flags = winParams.flags or bits
    } else {
        winParams.flags = winParams.flags and bits.inv()
    }
    win.attributes = winParams
}

fun getStatusBarHeight(context: Context): Int {
    val resources = context.resources

    var result = 0
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Activity.setupEndTransformation(){
    onTransformationEndContainer(intent.getParcelableExtra("com.skydoves.transformationlayout"))
}

fun setLightStatusBar(activity: Activity){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags: Int =
            activity.window.decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity.window.decorView.systemUiVisibility = flags
    }
}

fun clearLightStatusBar(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = activity.window.decorView.systemUiVisibility
        flags =
            flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity.window.decorView.systemUiVisibility = flags
    }
}