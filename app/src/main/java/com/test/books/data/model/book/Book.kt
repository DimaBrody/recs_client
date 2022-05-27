package com.test.books.data.model.book

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    var id: String? = null,
    var name: String? = null,
    var author: String? = null,
    var price: Double? = null,
    var rating: Double? = null,
    var quote: String? = null,
    var coverUrl: String? = null,
    var bookDetails: BookDetails? = null
) : Parcelable