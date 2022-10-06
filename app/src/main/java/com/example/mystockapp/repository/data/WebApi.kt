package com.example.mystockapp.repository.data

import com.example.mystockapp.model.StocksResponse
import retrofit2.http.*

interface WebApi {
    @GET("portfolio.json")
    suspend fun getStocks(): StocksResponse

    @GET("portfolio_malformed.json")
    suspend fun getBadStocks(): StocksResponse

    @GET("portfolio_empty.json")
    suspend fun getEmptyStocks(): StocksResponse
}