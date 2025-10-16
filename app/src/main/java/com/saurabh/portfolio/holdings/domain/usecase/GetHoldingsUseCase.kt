package com.saurabh.portfolio.holdings.domain.usecase

import com.saurabh.portfolio.core.common.Result
import com.saurabh.portfolio.holdings.domain.model.Holding
import com.saurabh.portfolio.holdings.domain.repository.HoldingsRepository

class GetHoldingsUseCase(private val repo: HoldingsRepository) {
    suspend operator fun invoke(): Result<List<Holding>> = repo.getHoldings()
}