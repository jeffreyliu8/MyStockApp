package com.example.mystockapp.repository.impl

import com.example.mystockapp.model.StocksResponse
import com.example.mystockapp.repository.WebRepository
import com.example.mystockapp.repository.data.WebApi


class WebRepositoryImpl(
    private var webApi: WebApi,
) : WebRepository {
    override suspend fun getStocks(): StocksResponse {
        return webApi.getStocks()
    }
}

