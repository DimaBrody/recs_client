package com.test.books.ui.store.discover

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.test.books.R
import com.test.books.databinding.FragmentDiscoverBinding
import com.test.books.network.api.BooksService
import com.test.books.ui.base.BaseFragment
import com.test.books.ui.store.discover.adapters.DiscoverServerAdapter
import com.test.books.utils.delegates.navController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>(R.layout.fragment_discover) {

    @Inject
    lateinit var adapter: DiscoverServerAdapter

    val viewModel: DiscoverViewModel by activityViewModels()

    private val navController by navController()

    override fun bindData(binding: FragmentDiscoverBinding) {


        adapter.setOnItemClickListener { book, items ->
//            navController?.navigateDetails(book, *items)
//            navController?.navigateProfile(book, *items)
        }

        binding.srl.setOnRefreshListener {
            viewModel.booksData.value = listOf()

            loadData()
        }

        loadData()

        binding.lifecycleOwner = this
        binding.discoverRecyclerView.adapter = adapter
        binding.adapter = adapter


        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.discoverRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val topRowVerticalPosition =
                    if (recyclerView.childCount == 0) 0 else recyclerView.getChildAt(
                        0
                    ).top
                binding.srl.isEnabled = topRowVerticalPosition >= 0
            }
        })
    }

    private fun loadData() {
        binding.srl.isRefreshing = false
        viewModel.getUser()
        GlobalScope.launch(Dispatchers.Main) {
            delay(200)
            viewModel.loadData(BooksService.USER_USER)
            delay(200)
            viewModel.loadData(BooksService.CONTENT_BASED)
            delay(200)
            viewModel.loadData(BooksService.MATRIX)
            delay(200)
            viewModel.loadData(BooksService.RBM)
            delay(200)
            viewModel.loadData(BooksService.HYBRID)
        }

    }

}