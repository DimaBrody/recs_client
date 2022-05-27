package com.test.books.ui.store.discover

import com.test.books.R
import com.test.books.databinding.FragmentDiscoverBinding
import com.test.books.ui.base.BaseFragment
import com.test.books.ui.store.discover.adapters.DiscoverMainAdapter
import com.test.books.utils.delegates.navController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>(R.layout.fragment_discover) {

    @Inject
    lateinit var adapter: DiscoverMainAdapter

    private val navController by navController()

    override fun bindData(binding: FragmentDiscoverBinding) {
        adapter.setOnItemClickListener { book, items ->
//            navController?.navigateDetails(book, *items)
//            navController?.navigateProfile(book, *items)
        }
        binding.adapter = adapter
    }

}