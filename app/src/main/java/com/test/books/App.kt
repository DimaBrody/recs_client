package com.test.books

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        debug = resources.getBoolean(R.bool.debug_mode)

//        FirebaseApp.initializeApp(this)
    }

    companion object {
        var debug: Boolean = true; private set
    }
}