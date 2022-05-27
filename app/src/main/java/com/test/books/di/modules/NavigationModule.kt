package com.test.books.di.modules

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.test.books.navigation.NavigationController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {

    @Provides
    @ActivityScoped
    fun provideNavigationController(activity: Activity) : NavigationController {
        return NavigationController(activity as? FragmentActivity)
    }

}