package com.test.books.di.modules.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.books.di.modules.qualifiers.ApiOkHttpClient
import com.test.books.network.api.BooksService
import com.test.books.network.data.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): BooksService {
        return retrofit.create(BooksService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @ApiOkHttpClient okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(ApiConstants.API_LINK).client(okHttpClient)
            .addConverterFactory(converterFactory).build()
    }

    @Provides
    @Singleton
    fun provideGsonFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()


}