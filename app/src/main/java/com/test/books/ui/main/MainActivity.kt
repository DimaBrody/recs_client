package com.test.books.ui.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.skydoves.transformationlayout.onTransformationStartContainer
import com.test.books.R
import com.test.books.data.model.MockUtil
import com.test.books.ui.base.BaseActivity
import com.test.books.databinding.ActivityMainBinding
import com.test.books.ui.main.listeners.NavigationListener
import com.test.books.utils.ui.setStatusBarGradient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        setStatusBarGradient(this)

        navController.setup(R.id.main_container)

        binding.navListener = NavigationListener(
            navController::navigateLibrary,
            navController::navigateStore,
            navController::navigateProfile
        )
    }

    fun expandNavigationBar(toggle: Boolean) {
        val animationTranslation = resources.getDimensionPixelSize(R.dimen.actionBarSize).toFloat()
        val animator = ObjectAnimator.ofFloat(
            binding.mainBottomNavigationContainer,
            View.TRANSLATION_Y,
            if (!toggle) 0f  else animationTranslation
        )

        animator.setDuration(500L).start()
    }
}