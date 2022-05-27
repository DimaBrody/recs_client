package com.test.books.ui.store.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.books.data.model.book.Book
import com.test.books.data.model.book.BooksHolder
import com.test.books.data.model.result.ResultOf
import com.test.books.data.repository.BookRepository
import com.test.books.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : BaseViewModel(), DiscoverViewModelDelegate {


    override val booksFlow: MutableStateFlow<BooksHolder> =
        MutableStateFlow(BooksHolder.createInitial())

    fun loadData() = mainScope {
        bookRepository.getBooks().map {
            BooksHolder.transformResult(it)
        }.combine(booksFlow) { newData, savedData ->

        }.collectLatest { result ->
        }
    }


}

interface DiscoverViewModelDelegate {
    val booksFlow: StateFlow<BooksHolder>
}