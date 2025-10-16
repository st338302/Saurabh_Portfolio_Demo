package com.saurabh.portfolio.holdings.data.repo

import android.util.Log
import com.saurabh.portfolio.core.common.Result
import com.saurabh.portfolio.holdings.data.remote.HoldingsApi
import com.saurabh.portfolio.holdings.domain.model.Holding
import com.saurabh.portfolio.holdings.domain.repository.HoldingsRepository
import jakarta.inject.Inject

class HoldingsRepositoryImpl @Inject constructor(
    private val api: HoldingsApi
) : HoldingsRepository {
    override suspend fun getHoldings(): Result<List<Holding>> {
        return try {
            val response = api.fetchHoldings()
            val domain = response.data.userHolding.map { dto ->
                Holding(
                    symbol = dto.symbol,
                    quantity = dto.quantity,
                    ltp = dto.ltp,
                    avgPrice = dto.avgPrice,
                    close = dto.close
                )
            }
            Result.Success(domain)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}