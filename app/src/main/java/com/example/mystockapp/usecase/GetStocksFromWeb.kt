package com.example.mystockapp.usecase

import android.util.Log
import com.example.mystockapp.model.Stock
import com.example.mystockapp.repository.WebRepository

class GetStocksFromWeb(
    private val webRepository: WebRepository,
) {
    suspend operator fun invoke(): Result<List<Stock>> {
        return try {
            val rsp = webRepository.getStocks()
            Result.success(rsp.stocks)
        } catch (e: Exception) {
            Log.e("GetStocksFromWeb", "exception: ${e.message}")
            Result.failure(e)
        }
    }
}
