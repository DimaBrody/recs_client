package com.test.books.ui.base

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.books.utils.segregation.setOnScrollStateChangedListener

abstract class BaseAnimatedAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private var lastItem: Int = -1
    private var onAttached = true

    protected fun animateItem(view: View, position: Int) {
        if (position > lastItem) {
            fadeInItem(view, if (onAttached) position else -1)
            lastItem = position
        }
    }

    private fun fadeInItem(view: View, mPos: Int) {
        val notFirstItem = mPos == -1
        val position = mPos + 1
        view.alpha = 0f
        val animatorSet = AnimatorSet()
        val animatorAlpha = ObjectAnimator.ofFloat(
            view, View.ALPHA, 0f, .5f, 1f
        )
        animatorAlpha.startDelay = DURATION_DELAY_IN_FADE + if (notFirstItem)
            DURATION_IN_FADE / 4 else (position * DURATION_IN_FADE / 3)
        animatorAlpha.duration = DURATION_IN_FADE
        animatorSet.play(animatorAlpha)
        animatorSet.start()
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.setOnScrollStateChangedListener {
            onAttached = false
        }
    }

    companion object {
        private const val DURATION_IN_FADE: Long = 200
        private const val DURATION_DELAY_IN_FADE: Long = 0
    }
}