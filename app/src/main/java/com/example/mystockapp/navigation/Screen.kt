package com.example.mystockapp.navigation


const val STOCK_DETAIL_EXTRA_INPUT_ID_KEY = "stockId"

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")

    object DetailScreen : Screen("detail_screen/{$STOCK_DETAIL_EXTRA_INPUT_ID_KEY}") {
        fun passStockId(id: String): String {
            return "detail_screen/$id"
        }
    }
}