package com.example.mystockapp.util

import java.io.IOException
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


fun convertForCurrency(currencyName: String, currentPriceCents: Int): String {
    if (currencyName != "USD") {
        throw IOException("unsupported currency")
    }
    val payment = BigDecimal(currentPriceCents).movePointLeft(2)
    val usa = Locale("en", "US")
    val dollarFormat = NumberFormat.getCurrencyInstance(usa)
    return dollarFormat.format(payment)
}