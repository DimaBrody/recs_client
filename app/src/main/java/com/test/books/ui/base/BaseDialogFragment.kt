package com.test.books.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.test.books.R

abstract class BaseDialogFragment<V : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        created()
    }

    open fun created() {
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = getAnimation()
    }

    open fun getAnimation(): Int = ANIM_SCALE

    companion object {
        const val ANIM_SCALE = R.style.ScaleDialogAnimation
        const val ANIM_FADE = R.style.FadeDialogAnimation
    }

}