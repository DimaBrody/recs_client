package com.test.books.ui.store.discover.more

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.test.books.data.model.book.BookServer
import com.test.books.data.model.book.BooksHolder
import com.test.books.data.repository.BookRepository
import com.test.books.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

class MoreViewModel @ViewModelInject constructor(
    private val bookRepository: BookRepository
) : BaseViewModel() {

    val booksData: MutableLiveData<List<BookServer>> =
        MutableLiveData<List<BookServer>>(listOf())

    fun loadData(type: String, count: Int = 100) = mainScope {
        bookRepository.getData(type, count).map {
            BooksHolder.transformResult(it, type)
        }.collectLatest {
            val list = mutableListOf<BookServer>()
            list.addAll(booksData.value!!)
            list.addAll(it.data)

            booksData.postValue(list)
        }
    }

}