package com.test.books.di.modules

import com.squareup.picasso.Picasso
import com.test.books.ui.store.discover.adapters.DiscoverServerAdapter
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
    fun provideMainProvider(picasso: Picasso): DiscoverServerAdapter {
        return DiscoverServerAdapter(picasso)
    }
}