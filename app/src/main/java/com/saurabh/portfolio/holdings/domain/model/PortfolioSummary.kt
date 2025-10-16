package com.saurabh.portfolio.holdings.domain.model

data class PortfolioSummary(
    val currentValue: Double,
    val totalInvestment: Double,
    val totalPnl: Double,
    val todaysPnl: Double
) {
    fun getTotalPnLPercent(): Double = (totalPnl / totalInvestment) * 100
}
