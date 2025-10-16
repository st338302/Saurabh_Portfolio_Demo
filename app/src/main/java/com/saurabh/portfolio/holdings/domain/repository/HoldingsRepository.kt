package com.saurabh.portfolio.holdings.domain.repository

import com.saurabh.portfolio.core.common.Result
import com.saurabh.portfolio.holdings.domain.model.Holding

interface HoldingsRepository {
    suspend fun getHoldings(): Result<List<Holding>>
}
