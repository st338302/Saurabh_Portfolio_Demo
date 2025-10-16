package com.saurabh.portfolio.holdings.data.remote

import retrofit2.http.GET

interface HoldingsApi {
    @GET("/")
    suspend fun fetchHoldings(): HoldingsResponse
}
