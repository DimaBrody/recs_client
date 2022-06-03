package com.test.books.data.model.book

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// {"id": 105358, "isbn": "0439139597", "title": "Harry Potter and the Goblet of Fire (Book 4)", "author":
// "J. K. Rowling", "year": 2000, "image_s": "http://images.amazon.com/images/P/0439139597.01.THUMBZZZ.jpg"
// , "image_m": "http://images.amazon.com/images/P/0439139597.01.MZZZZZZZ.jpg", "image_l":
// "http://images.amazon.com/images/P/0439139597.01.LZZZZZZZ.jpg", "publisher": "Scholastic"
@Parcelize
data class BookServer(
    var id: Int? = null,
    var isbn: String? = null,
    var title: String? = null,
    var author: String? = null,
    var year: Int? = null,
    var publisher: String? = null,
    var rating: Int? = null,
    @SerializedName("image_s")
    var imageSmall: String? = null,
    @SerializedName("image_m")
    var imageMedium: String? = null,
    @SerializedName("image_l")
    var imageLarge: String? = null
) : Parcelable