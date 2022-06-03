package com.test.books.di.modules.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.books.data.repository.BookRepository
import com.test.books.di.modules.ViewModelFactory
import com.test.books.di.modules.ViewModelKey
import com.test.books.ui.store.discover.DiscoverViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class ViewModelModule {
//    @Binds
//    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
//
//    //You are able to declare ViewModelProvider.Factory dependency in another module. For example in ApplicationModule.
//    @Binds
//    @IntoMap
//    @ViewModelKey(DiscoverViewModel::class)
//    abstract fun discoverViewModel(bookRepository: BookRepository): ViewModel //Others ViewModels
//}