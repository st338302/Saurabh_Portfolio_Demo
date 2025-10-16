package com.saurabh.portfolio.di

import com.saurabh.portfolio.holdings.data.remote.HoldingsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl("https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build()).build()


    @Provides
    @Singleton
    fun provideHoldingsApi(retrofit: Retrofit): HoldingsApi =
        retrofit.create(HoldingsApi::class.java)

}