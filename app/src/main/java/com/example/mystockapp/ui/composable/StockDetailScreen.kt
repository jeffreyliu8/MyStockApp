package com.example.mystockapp.ui.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mystockapp.model.StockDetailScreenViewModelState
import com.example.mystockapp.ui.theme.MyStockAppTheme


@Composable
fun StockDetailScreen(
    modifier: Modifier = Modifier,
    uiState: StockDetailScreenViewModelState,
    upPress: () -> Unit = {},
) {

}


@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenPreview() {
    MyStockAppTheme() {
        StockDetailScreen(
            uiState = StockDetailScreenViewModelState()
        )
    }
}
