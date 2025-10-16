package com.saurabh.portfolio.holdings.domain.usecase

import com.saurabh.portfolio.core.common.Result
import com.saurabh.portfolio.holdings.domain.model.Holding
import com.saurabh.portfolio.holdings.domain.repository.HoldingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetHoldingsUseCaseTest {

    private lateinit var repo: HoldingsRepository
    private lateinit var useCase: GetHoldingsUseCase

    @Before
    fun setup() {
        repo = mockk()
        useCase = GetHoldingsUseCase(repo)
    }

    @Test
    fun `returns data from repository`() = runBlocking {
        val holdings = listOf(Holding("XYZ", 1.0, 10.0, 8.0, 9.0))
        coEvery { repo.getHoldings() } returns Result.Success(holdings)

        val result = useCase()
        assertTrue(result is Result.Success)
        assertEquals(1, (result as Result.Success).data.size)
    }

    @Test
    fun `returns error from repository`() = runBlocking {
        val err = Result.Error(RuntimeException("404"))
        coEvery { repo.getHoldings() } returns err

        val result = useCase()
        assertSame(err, result)
    }
}
