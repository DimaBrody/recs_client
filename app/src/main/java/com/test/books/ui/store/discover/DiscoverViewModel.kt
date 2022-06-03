package com.test.books.ui.store.discover

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.books.data.model.book.BooksHolder
import com.test.books.data.model.response.RecommendationResponse
import com.test.books.data.model.result.ResultOf
import com.test.books.data.repository.BookRepository
import com.test.books.ui.base.BaseViewModel
import com.test.books.utils.functions.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

class DiscoverViewModel @ViewModelInject constructor(
    private val bookRepository: BookRepository
) : BaseViewModel(), DiscoverViewModelDelegate {

    override val booksData: MutableLiveData<List<BooksHolder>> =
        MutableLiveData<List<BooksHolder>>(listOf())

    fun loadData(type: String) = mainScope {
        bookRepository.getData(type).map {
            BooksHolder.transformResult(it, type)
        }.collectLatest {
            val list = mutableListOf<BooksHolder>()
            list.addAll(booksData.value!!)
            list.add(it)

            booksData.postValue(list)
        }
    }

    fun getUser() = mainScope {
        bookRepository.getUser().map {
            BooksHolder.transformResult(it)
        }.collectLatest {
            val list = mutableListOf<BooksHolder>()
            list.addAll(booksData.value!!)
            list.add(it)

            booksData.postValue(list)
        }
    }

}
interface DiscoverViewModelDelegate {
    val booksData: LiveData<List<BooksHolder>>
}