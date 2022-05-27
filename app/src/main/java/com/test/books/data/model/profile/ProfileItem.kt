package com.test.books.data.model.profile

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class ProfileItem(
    val text: String,
    @DrawableRes val icon: Int,
    @ColorRes val iconTint: Int,
    val callback: () -> Unit
)