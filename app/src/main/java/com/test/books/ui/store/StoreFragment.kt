package com.test.books.ui.store

import android.os.Bundle
import android.view.View
import com.test.books.R
import com.test.books.databinding.FragmentStoreBinding
import com.test.books.ui.base.BaseFragment
import com.test.books.utils.animations.animateFrom
import com.test.books.utils.ui.setStatusBarGradient

class StoreFragment : BaseFragment<FragmentStoreBinding>(R.layout.fragment_store) {

    private lateinit var binding: FragmentStoreBinding

    override fun bindData(binding: FragmentStoreBinding) {
        this.binding = binding

        binding.storeViewPager.adapter = StorePagerAdapter.create(childFragmentManager)
        binding.storeTabLayout.setupWithViewPager(binding.storeViewPager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}