package com.test.books.utils.animations

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.annotation.AnimRes
import androidx.core.animation.addListener
import com.test.books.ui.views.images.CrossfadeImageView
import com.test.books.ui.views.images.OverlayImageView
import java.lang.IllegalArgumentException
import java.lang.ref.WeakReference

const val DEFAULT_DURATION = 200L
const val DEFAULT_FADE_DURATION = 250L
const val DEFAULT_START_ANIMATION_VALUE = 0f
const val DEFAULT_END_ANIMATION_VALUE = 1f
const val ZERO = 0L

const val FRAGMENT_FADE_DURATION = 150L

const val EXPANDABLE_TEXT_DURATION = 200L

const val DETAILS_ALPHA = .8f


fun <V : View> V.fadeIn(
    duration: Long = DEFAULT_FADE_DURATION,
    delay: Long = ZERO,
    onEnd: (Animator?) -> Unit = {}
) = reference { view ->
    anim(
        view?.alpha ?: DEFAULT_START_ANIMATION_VALUE,
        DEFAULT_END_ANIMATION_VALUE,
        duration,
        onStart = {
            alpha = 0f
            visibility = View.VISIBLE
        },
        delay = delay,
        onEnd = onEnd
    ) {
        view?.alpha = it.animatedValue as Float
    }
}

fun <V : View> V.fadeOut(
    duration: Long = DEFAULT_FADE_DURATION,
    delay: Long = ZERO,
    onEnd: (Animator?) -> Unit = {}
) = reference { view ->
    anim(
        view?.alpha ?: DEFAULT_END_ANIMATION_VALUE,
        DEFAULT_START_ANIMATION_VALUE,
        duration,
        delay = delay,
        onEnd = {
            visibility = View.GONE
            alpha = 0f
            onEnd(it)
        }
    ) {
        view?.alpha = it.animatedValue as Float
    }
}

fun <V> V.fadeTo(
    to: Float = DEFAULT_START_ANIMATION_VALUE,
    duration: Long = DEFAULT_FADE_DURATION,
    delay: Long = ZERO,
    onEnd: (Animator?) -> Unit = {}
) where V : View = reference { view ->
    anim(view?.alpha ?: 1f, to, duration, delay = delay, onEnd = onEnd) {
        view?.alpha = it.animatedValue as Float
    }
}



fun <V : View> V.animateFrom(@AnimRes id: Int) = reference { view ->
    val animation = AnimationUtils.loadAnimation(view?.context, id)
    view?.startAnimation(animation)
}


fun anim(
    from: Number = DEFAULT_START_ANIMATION_VALUE,
    to: Number = DEFAULT_END_ANIMATION_VALUE,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = LinearInterpolator(),
    repeatMode: Int? = null,
    repeatCount: Int? = null,
    delay: Long? = null,
    onStart: (Animator?) -> Unit = {},
    onEnd: (Animator?) -> Unit = {},
    onUpdateListener: (ValueAnimator) -> Unit = {}
) {
    val animator = when (to) {
        is Long, is Int -> ValueAnimator.ofInt(from.toInt(), to.toInt())
        is Float -> ValueAnimator.ofFloat(from.toFloat(), to)
        else -> throw IllegalArgumentException("Wrong number for Animation.")
    }

    animator.duration = duration
    animator.interpolator = interpolator

    repeatCount?.let { animator.repeatCount = it }
    repeatMode?.let { animator.repeatMode = it }

    delay?.let { animator.startDelay = it }

    animator.addUpdateListener {
        onUpdateListener(it)
    }

    animator.addListener(onEnd, onStart)

    animator.start()
}

inline fun <V : View> V.reference(crossinline onReference: (V?) -> Unit) {
    onReference(WeakReference(this).get())
}