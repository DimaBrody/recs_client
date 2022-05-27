package com.test.books.di.modules

import com.test.books.utils.helpers.BlurryHelper
import com.test.books.ui.store.discover.adapters.DiscoverMainAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object AdaptersModule {

    @FragmentScoped
    @Provides
    fun provideMainProvider(blurryHelper: BlurryHelper) : DiscoverMainAdapter {
        return DiscoverMainAdapter(blurryHelper)
    }
}