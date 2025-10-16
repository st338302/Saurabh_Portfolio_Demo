package com.saurabh.portfolio.core.common

import java.text.NumberFormat
import java.util.*

fun Double.formatMoney(): String =
    NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)

fun Double.formatPercent(): String = String.format(Locale.getDefault(), "%.2f%%", this)
