package com.test.books.navigation

import android.content.Intent
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.test.books.R
import com.test.books.data.model.book.Book
import com.test.books.ui.details.DetailsActivity
import com.test.books.ui.main.MainActivity
import com.test.books.ui.profile.ProfileFragment
import com.test.books.ui.store.StoreFragment
import com.test.books.utils.delegates.weak
import com.test.books.utils.functions.checkNullable
import com.test.books.utils.functions.ifNotEmpty
import com.test.books.utils.views.TransactionPair


class NavigationController(activity: FragmentActivity?) {

    private val fragmentManager by weak(activity?.supportFragmentManager)

    @IdRes
    private var containerId: Int? = null

    fun setup(containerId: Int) {
        this.containerId = containerId
    }

    fun navigateStore() {
        navigate(StoreFragment(), FragmentParameters(
            fragmentAnimations = FragmentAnimations.createNone()
        ))
    }

    fun navigateLibrary() {

    }

    fun navigateProfile() {
        navigate(
            ProfileFragment(), FragmentParameters(
                isAddToBackStack = true,
                fragmentAnimations = FragmentAnimations.createNone()
            )
        )
    }


    private fun navigate(
        fragment: Fragment?,
        parameters: FragmentParameters = FragmentParameters()
    ) {
        checkNullable(fragmentManager, containerId, fragment) {
            val currentFragment = fragmentManager!!.findFragmentById(containerId!!)
            val beginTransaction = fragmentManager!!.beginTransaction()


            val navigateTransaction = if (parameters.isAddFragment)
                beginTransaction.add(containerId!!, fragment!!)
            else beginTransaction.replace(containerId!!, fragment!!)

            if (parameters.isAddToBackStack)
                navigateTransaction.addToBackStack(fragment.tag)

            parameters.transactions.ifNotEmpty { transactions ->
                transactions.forEach {
                    navigateTransaction.addSharedElement(it.first!!, it.second!!)
                }
                currentFragment?.let { currentFragment ->
                    navigateTransaction.detach(currentFragment)
                }
            }

            parameters.fragmentAnimations?.let {
                if (it.popEnter != null && it.popExit != null)
                    navigateTransaction.setCustomAnimations(
                        it.enter, it.exit, it.popEnter, it.popEnter
                    )
                else navigateTransaction.setCustomAnimations(it.enter, it.exit)
            }

            navigateTransaction.setReorderingAllowed(true).commitAllowingStateLoss()
        }
    }

    private data class FragmentParameters(
        val isAddToBackStack: Boolean = false,
        val isAddFragment: Boolean = false,
        val transactions: List<TransactionPair> = emptyList(),
        val fragmentAnimations: FragmentAnimations? = null
    )

    class FragmentAnimations private constructor(
        @AnimRes val enter: Int,
        @AnimRes val exit: Int,
        @AnimRes val popEnter: Int? = null,
        @AnimRes val popExit: Int? = null
    ) {
        companion object {
            fun create(
                @AnimRes enter: Int,
                @AnimRes exit: Int,
                @AnimRes popEnter: Int,
                @AnimRes popExit: Int
            ) = FragmentAnimations(enter, exit, popEnter, popExit)

            fun createBoth(
                @AnimRes enter: Int,
                @AnimRes exit: Int
            ) = FragmentAnimations(enter, exit, enter, exit)

            fun createOnly(
                @AnimRes enter: Int,
                @AnimRes exit: Int
            ) = FragmentAnimations(enter, exit)

            fun createNone() = FragmentAnimations(
                R.anim.anim_none,
                R.anim.anim_none
            )
        }
    }
}