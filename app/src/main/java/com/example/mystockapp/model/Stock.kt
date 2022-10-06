package com.example.mystockapp.model

data class Stock(
    val ticker: String,
    val name: String,
    val currency: String,
    val current_price_cents: Int,
    val quantity: Int?,
    val current_price_timestamp: Long,
)