package com.example.mystockapp.repository

import com.example.mystockapp.model.StocksResponse

interface WebRepository {
    suspend fun getStocks(): StocksResponse
}