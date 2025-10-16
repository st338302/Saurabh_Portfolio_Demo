package com.saurabh.portfolio.holdings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saurabh.portfolio.core.common.Result
import com.saurabh.portfolio.holdings.domain.usecase.GetHoldingsUseCase
import com.saurabh.portfolio.holdings.domain.model.Holding
import com.saurabh.portfolio.holdings.domain.model.PortfolioSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HoldingsViewModel @Inject constructor(
    private val getHoldings: GetHoldingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HoldingsUiState())
    val uiState: StateFlow<HoldingsUiState> = _uiState.asStateFlow()

    init {
        fetchHoldings()
    }

    fun fetchHoldings() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val res = getHoldings()) {
                is Result.Success -> {
                    val holdings = res.data
                    val summary = calculateSummary(holdings)
                    _uiState.update {
                        it.copy(isLoading = false, holdings = holdings, summary = summary)
                    }
                }

                is Result.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = res.throwable.message) }
                }
            }
        }
    }

    private fun calculateSummary(holdings: List<Holding>): PortfolioSummary {
        val currentValue = holdings.sumOf { it.ltp * it.quantity }
        val totalInvestment = holdings.sumOf { it.avgPrice * it.quantity }
        val totalPnl = currentValue - totalInvestment
        val todaysPnl = holdings.sumOf { (it.close - it.ltp) * it.quantity }
        return PortfolioSummary(currentValue, totalInvestment, totalPnl, todaysPnl)
    }

    fun toggleExpanded(symbol: String) {
        _uiState.update {
            val toggled = it.expandedSymbols.toMutableSet()
            if (!toggled.add(symbol)) toggled.remove(symbol)
            it.copy(expandedSymbols = toggled)
        }
    }
}