package com.example.mystockapp.model


data class StockListViewModelState(
    val stocks: List<Stock> = emptyList(),
)