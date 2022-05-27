package com.test.books.data.model.book

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookDetails(
    var year: String? = null,
    var pages: String? = null
) : Parcelable