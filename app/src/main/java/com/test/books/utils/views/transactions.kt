package com.test.books.utils.views

import android.view.View
import androidx.core.util.Pair

typealias TransactionPair = Pair<View, String>

fun <V : View> V.toTransitionName(): TransactionPair = Pair(this, this.transitionName)