package com.example.mystockapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mystockapp.model.StockDetailScreenViewModelState
import com.example.mystockapp.ui.SnackBarManager
import com.example.mystockapp.usecase.MainActivityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class StockDetailScreenViewModel @Inject constructor(
    private val useCases: MainActivityUseCases,
    private val snackBarManager: SnackBarManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StockDetailScreenViewModelState())
    val uiState: StateFlow<StockDetailScreenViewModelState> = _uiState


    fun setTicker(ticker: String?) {
        if (ticker.isNullOrBlank()) {
            return
        }
//        _uiState.update {
//            it.copy(stock = ticker)
//        }
    }
}