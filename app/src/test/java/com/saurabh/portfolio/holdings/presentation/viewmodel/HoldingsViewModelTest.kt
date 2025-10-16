package com.saurabh.portfolio.holdings.presentation.viewmodel

import com.saurabh.portfolio.core.common.Result
import com.saurabh.portfolio.holdings.domain.model.Holding
import com.saurabh.portfolio.holdings.domain.repository.HoldingsRepository
import com.saurabh.portfolio.holdings.domain.usecase.GetHoldingsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class HoldingsViewModelTest {
    private val repo = mockk<HoldingsRepository>()
    private val getHoldings = GetHoldingsUseCase(repo)
    private lateinit var viewModel: HoldingsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun teardown() { Dispatchers.resetMain() }

    @Test
    fun `fetch holdings success updates state`() = runTest {
        val sample = listOf(Holding("ABC", 10.0, 100.0, 90.0, 95.0))
        coEvery { repo.getHoldings() } returns Result.Success(sample)
        viewModel = HoldingsViewModel(getHoldings)
        advanceUntilIdle()
        val state = viewModel.uiState.first { !it.isLoading }
        assertEquals(1, state.holdings.size)
        assertNotNull(state.summary)
    }

    @Test
    fun `fetch holdings error sets error`() = runTest {
        coEvery { repo.getHoldings() } returns Result.Error(RuntimeException("net"))
        viewModel = HoldingsViewModel(getHoldings)
        advanceUntilIdle()
        val state = viewModel.uiState.first { !it.isLoading }
        assertEquals("net", state.error)
    }
}