package com.example.mystockapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystockapp.R
import com.example.mystockapp.model.StockListViewModelState
import com.example.mystockapp.ui.SnackBarManager
import com.example.mystockapp.usecase.StockListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListScreenViewModel @Inject constructor(
    private val useCases: StockListUseCases,
    private val snackBarManager: SnackBarManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StockListViewModelState())
    val uiState: StateFlow<StockListViewModelState> = _uiState

    init {
        viewModelScope.launch {
            val result = useCases.getStocksFromWeb()
            if (result.isSuccess) {
                result.getOrNull()?.let { stocks ->
                    _uiState.update {
                        it.copy(stocks = stocks)
                    }
                }
            } else {
                snackBarManager.showMessage(
                    R.string.something_went_wrong,
                    result.exceptionOrNull()?.message
                )
            }
        }
    }
}