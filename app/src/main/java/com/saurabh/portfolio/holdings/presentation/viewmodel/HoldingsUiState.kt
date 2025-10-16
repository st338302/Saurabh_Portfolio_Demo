package com.saurabh.portfolio.holdings.presentation.viewmodel

import com.saurabh.portfolio.holdings.domain.model.Holding
import com.saurabh.portfolio.holdings.domain.model.PortfolioSummary

data class HoldingsUiState(
    val isLoading: Boolean = false,
    val holdings: List<Holding> = emptyList(),
    val summary: PortfolioSummary? = null,
    val error: String? = null,
    val expandedSymbols: Set<String> = emptySet()
)
