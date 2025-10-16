package com.saurabh.portfolio.holdings.data.repo

import com.saurabh.portfolio.core.common.Result
import com.saurabh.portfolio.holdings.data.remote.HoldingsApi
import com.saurabh.portfolio.holdings.data.remote.HoldingsData
import com.saurabh.portfolio.holdings.data.remote.HoldingsResponse
import com.saurabh.portfolio.holdings.data.remote.RemoteHolding
import com.saurabh.portfolio.holdings.domain.model.Holding
import com.saurabh.portfolio.holdings.domain.repository.HoldingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.collections.first

class HoldingsRepositoryImplTest {

    private lateinit var api: HoldingsApi
    private lateinit var repo: HoldingsRepository

    @Before
    fun setup() {
        api = mockk()
        repo = HoldingsRepositoryImpl(api)
    }

    @Test
    fun `getHoldings returns Success when api succeeds`() = runBlocking {
        val remote = listOf(
            RemoteHolding("ABC", 10.0, 100.0, 90.0, 95.0)
        )
        coEvery { api.fetchHoldings() } returns HoldingsResponse(HoldingsData(remote))

        val result = repo.getHoldings()
        assertTrue(result is Result.Success)
        val list = (result as Result.Success<List<Holding>>).data
        assertEquals(1, list.size)
        assertEquals("ABC", list.first().symbol)
    }

    @Test
    fun `getHoldings returns Error when api throws`() = runBlocking {
        coEvery { api.fetchHoldings() } throws RuntimeException("Network")

        val result = repo.getHoldings()
        assertTrue(result is Result.Error)
        assertEquals("Network", (result as Result.Error).throwable.message)
    }
}
