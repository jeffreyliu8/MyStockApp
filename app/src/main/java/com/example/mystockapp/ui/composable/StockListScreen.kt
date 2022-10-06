package com.example.mystockapp.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mystockapp.model.Stock
import com.example.mystockapp.model.StockListViewModelState
import com.example.mystockapp.ui.theme.MyStockAppTheme


@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    uiState: StockListViewModelState,
    onStockSelected: (Stock) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(uiState.stocks, key = { it.ticker }) {
            Text(text = it.name)
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
