package com.example.mystockapp.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mystockapp.model.Stock
import com.example.mystockapp.model.StockListViewModelState
import com.example.mystockapp.ui.theme.MyStockAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    uiState: StockListViewModelState,
    onStockSelected: (Stock) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Stock App")
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(uiState.stocks, key = { it.ticker }) {
                Card(modifier = Modifier.fillMaxWidth(), onClick = { onStockSelected(it) }) {
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
    MyStockAppTheme() {
        StockListScreen(
            uiState = StockListViewModelState()
        )
    }
}
