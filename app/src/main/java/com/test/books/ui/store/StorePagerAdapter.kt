package com.test.books.ui.store

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.test.books.ui.store.discover.DiscoverFragment

class StorePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private val fragmentsList = mutableListOf<Pair<String, Fragment>>(
        "Главная" to DiscoverFragment()
    )

    override fun getCount(): Int = fragmentsList.size

    override fun getItem(position: Int): Fragment = fragmentsList[position].second

    override fun getPageTitle(position: Int): CharSequence = fragmentsList[position].first

    companion object {
        fun create(fragmentManager: FragmentManager) = StorePagerAdapter(fragmentManager)
    }
}