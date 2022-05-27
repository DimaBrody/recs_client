package com.test.books.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.test.books.databinding.FragmentStoreBinding
import com.test.books.ui.main.MainActivity
import com.test.books.utils.ui.setDefaultStatusBar

abstract class BaseFragment<V : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    protected val mainActivity: MainActivity?
        get() = activity as? MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<V>(
            inflater,
            layoutId,
            container,
            false
        )

        bindData(binding)

        return binding.root
    }

    abstract fun bindData(binding: V)

}