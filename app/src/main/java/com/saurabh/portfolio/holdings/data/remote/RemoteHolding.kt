package com.saurabh.portfolio.holdings.data.remote

data class RemoteHolding(
    val symbol: String,
    val quantity: Double,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
)

