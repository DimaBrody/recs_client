package com.test.books.ui.profile

import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.app.SharedElementCallback
import com.test.books.R
import com.test.books.data.model.MockUtil
import com.test.books.data.model.book.Book
import com.test.books.data.model.profile.ProfileItem
import com.test.books.databinding.FragmentProfileBinding
import com.test.books.ui.base.BaseFragment
import com.test.books.ui.profile.settings.DiscountsFragment
import com.test.books.utils.animations.FRAGMENT_FADE_DURATION
import com.test.books.utils.animations.animateFrom
import com.test.books.utils.animations.fadeIn
import com.test.books.utils.delegates.argument
import com.test.books.utils.ui.clearLightStatusBar
import com.test.books.utils.ui.setDefaultStatusBar
import com.test.books.utils.ui.setLightStatusBar
import com.test.books.utils.ui.setStatusBarGradient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {


    private lateinit var binding: FragmentProfileBinding


    override fun bindData(binding: FragmentProfileBinding) {
        this.binding = binding
        val profileData = provideProfileListData()

        binding.user = MockUtil.createUser()
        binding.adapter = ProfileAdapter(profileData)
    }

    private fun provideProfileListData() = mutableListOf(
        ProfileItem(
            "Акции",
            R.drawable.ic_star,
            R.color.profile_discounts,
            ::openDiscountsFragment
        ),
        ProfileItem(
            "Уведомления",
            R.drawable.ic_notifications,
            R.color.profile_notifications,
            ::openDiscountsFragment
        ),
        ProfileItem(
            "Приватность",
            R.drawable.ic_lock,
            R.color.profile_privacy,
            ::openDiscountsFragment
        ),
        ProfileItem("Выйти", R.drawable.ic_exit, R.color.profile_exit, ::openDiscountsFragment)
    )

    private fun openDiscountsFragment() {
        DiscountsFragment.show(childFragmentManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDefaultStatusBar(requireActivity(), R.color.white)
        setLightStatusBar(requireActivity())
        binding.profileContainer.animateFrom(R.anim.dialog_scale_in)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setStatusBarGradient(requireActivity())
        clearLightStatusBar(requireActivity())
    }


}