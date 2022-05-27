package com.test.books.ui.profile.settings

import androidx.fragment.app.FragmentManager
import com.test.books.R
import com.test.books.databinding.FragmentEmptyBinding
import com.test.books.ui.base.BaseDialogFragment

class DiscountsFragment : BaseDialogFragment<FragmentEmptyBinding>(R.layout.fragment_empty) {

    override fun bindData(binding: FragmentEmptyBinding) {

    }

    override fun getAnimation(): Int = ANIM_FADE

    companion object {
        fun show(fragmentManager: FragmentManager) {
            val discountsFragment = DiscountsFragment()
            discountsFragment.show(fragmentManager, discountsFragment::class.java.simpleName)
        }
    }
}