package com.test.books.data.model.book

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class BookUserReview(
    var id: String? = null,
    var avatar: String? = null,
    var username: String? = null,
    var date: Date? = null,
    var rating: Int? = null,
    var text: String? = null,
    var likes: Int? = null,
    var dislikes: Int? = null
) : Parcelable