package com.example.mystockapp.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mystockapp.R
import com.example.mystockapp.model.Stock
import com.example.mystockapp.model.StockListViewModelState
import com.example.mystockapp.ui.theme.MyStockAppTheme
import com.example.mystockapp.util.convertForCurrency
import com.example.mystockapp.util.convertLongToTime


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    uiState: StockListViewModelState,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onStockSelected: (Stock) -> Unit = {}
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Stock App")
                },
            )
        }
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if (uiState.stocks.isEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.no_stocks),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding() + 16.dp,
                        bottom = paddingValues.calculateBottomPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = modifier.fillMaxWidth()
                ) {
                    items(uiState.stocks, key = { it.ticker }) {
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Row {
                                    Text(
                                        text = it.ticker, modifier = Modifier.weight(1f),
                                        maxLines = 1, overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = convertForCurrency(
                                            it.currency,
                                            it.current_price_cents
                                        )
                                    )
                                }

                                Text(text = it.name)
                                val q = it.quantity ?: 0
                                Text(
                                    text = pluralStringResource(
                                        R.plurals.number_of_stocks,
                                        q, q
                                    )
                                )
                                Text(text = convertLongToTime(it.current_price_timestamp * 1000))
                            }
                        }
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
