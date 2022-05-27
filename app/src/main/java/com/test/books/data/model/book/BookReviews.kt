package com.test.books.data.model.book

import com.test.books.utils.functions.oneSymbolPrecision
import kotlinx.android.parcel.Parcelize
import java.lang.ArithmeticException

data class BookReviews(val reviewsMap: HashMap<Int, Int> = hashMapOf()) {

    var reviewsAmount: Int = -1
        get() = if (field >= 0) field else {
            var count = 0
            reviewsMap.keys.forEach {
                count += reviewsMap[it] ?: 0
            }
            count.also { field = count }
        }

    var reviewsAverageValue: Float = -1f
        get() = if (field >= 0) field else {
            var sum = 0f
            reviewsMap.keys.forEach {
                sum += it * (reviewsMap[it]?.toFloat() ?: 0f)
            }

            val result = try {
                sum / reviewsAmount
            } catch (e: ArithmeticException) {
                0f
            }

            result.also { field = result }
        }

    var maxAmount: Int = -1
        get() = if (field >= 0) field else
            (reviewsMap.values.maxOrNull() ?: 0)
                .also { field = it }


    val reviewsAverageString: String
        get() = reviewsAverageValue.oneSymbolPrecision().toString()

    fun getProgressValue(score: Int): Int {
        val percentage = reviewsMap[score]?.toFloat()?.div(maxAmount) ?: 0f

        return (percentage * 100).toInt()
    }

}