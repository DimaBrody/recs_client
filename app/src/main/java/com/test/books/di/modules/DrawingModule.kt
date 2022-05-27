package com.test.books.di.modules

import android.content.Context
import com.test.books.utils.helpers.BlurryHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DrawingModule {

    @Singleton
    @Provides
    fun provideBlurryComposer(@ApplicationContext context: Context): BlurryHelper {
        return BlurryHelper(context)
    }

}