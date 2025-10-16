package com.saurabh.portfolio.holdings.domain.model

data class Holding(
    val symbol: String,
    val quantity: Double,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
) {
    fun getPnL(): Double = ((ltp - avgPrice) * quantity)
}
