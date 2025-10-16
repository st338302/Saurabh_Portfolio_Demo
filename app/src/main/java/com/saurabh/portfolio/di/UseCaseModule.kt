package com.saurabh.portfolio.di

import com.saurabh.portfolio.holdings.domain.usecase.GetHoldingsUseCase
import com.saurabh.portfolio.holdings.domain.repository.HoldingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetHoldingsUseCase(repository: HoldingsRepository): GetHoldingsUseCase =
        GetHoldingsUseCase(repository)

}