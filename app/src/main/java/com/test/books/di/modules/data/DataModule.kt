package com.test.books.di.modules.data

import com.test.books.data.repository.BookRepository
import com.test.books.network.BooksClient
import com.test.books.network.api.BooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideBookRepository(booksClient: BooksClient): BookRepository =
        BookRepository(booksClient)

    @Singleton
    @Provides
    fun provideBooksClient(booksService: BooksService): BooksClient =
        BooksClient(booksService)
}