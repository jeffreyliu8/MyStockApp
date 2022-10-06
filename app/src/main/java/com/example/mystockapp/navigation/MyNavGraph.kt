package com.example.mystockapp.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mystockapp.ui.composable.StockDetailScreen
import com.example.mystockapp.ui.composable.StockListScreen
import com.example.mystockapp.viewmodel.StockDetailScreenViewModel
import com.example.mystockapp.viewmodel.StockListScreenViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun MyNavGraph(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    val appState =
        rememberMyAppState(navController = navController, coroutineScope = coroutineScope)

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) { backStackEntry ->
            val viewModel = hiltViewModel<StockListScreenViewModel>(backStackEntry)
            val uiState by viewModel.uiState.collectAsState()
            StockListScreen(
                uiState = uiState,
                onStockSelected = { appState.navigateToDetail(it.ticker, backStackEntry) }
            )
        }
        composable(Screen.DetailScreen.route) { backStackEntry ->
            val viewModel = hiltViewModel<StockDetailScreenViewModel>(backStackEntry)
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(true) {
                viewModel.setTicker(
                    ticker = backStackEntry.arguments?.getString(
                        STOCK_DETAIL_EXTRA_INPUT_ID_KEY
                    )
                )
            }

            StockDetailScreen(
                uiState = uiState,
                upPress = { appState.upPress() }
            )
        }
    }
}