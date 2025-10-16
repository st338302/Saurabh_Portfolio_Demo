package com.saurabh.portfolio.di

import com.saurabh.portfolio.holdings.data.repo.HoldingsRepositoryImpl
import com.saurabh.portfolio.holdings.domain.repository.HoldingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindHoldingsRepository(impl: HoldingsRepositoryImpl): HoldingsRepository
}