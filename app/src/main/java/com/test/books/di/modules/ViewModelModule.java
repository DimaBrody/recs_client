package com.test.books.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.test.books.data.repository.BookRepository;
import com.test.books.ui.store.discover.DiscoverViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;

//@Module
//@InstallIn(SingletonComponent.class)
//public abstract class ViewModelModule {
//
//    @Binds
//    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
//    //You are able to declare ViewModelProvider.Factory dependency in another module. For example in ApplicationModule.
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DiscoverViewModel.class)
//    abstract DiscoverViewModel discoverViewModel(BookRepository bookRepository);
//
//    //Others ViewModels
//}