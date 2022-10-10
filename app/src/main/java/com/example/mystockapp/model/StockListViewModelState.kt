package com.example.mystockapp.model


data class StockListViewModelState(
    val isLoading: Boolean = true,
    val stocks: List<Stock> = emptyList(),
)