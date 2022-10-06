package com.example.mystockapp.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mystockapp.model.StockDetailScreenViewModelState
import com.example.mystockapp.ui.theme.MyStockAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDetailScreen(
    modifier: Modifier = Modifier,
    uiState: StockDetailScreenViewModelState,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    upPress: () -> Unit = {},
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = upPress) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                title = {
                    Text(text = uiState.stock?.ticker ?: "")
                },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            uiState.stock?.let {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = it.ticker)
                        Text(text = it.name)
                        Text(text = it.currency)
                        Text(text = it.current_price_cents.toString())
                        Text(text = (it.quantity ?: 0).toString())
                        Text(text = it.current_price_timestamp.toString())
                    }
                }
            }
        }
    }
}


@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenPreview() {
    MyStockAppTheme {
        StockDetailScreen(
            uiState = StockDetailScreenViewModelState()
        )
    }
}
