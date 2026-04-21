package com.budgetnah.pro.presentation.features.settings

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {

    var isDarkMode by mutableStateOf(false)
        private set

    var selectedCurrency by mutableStateOf("XAF")
        private set

    fun toggleTheme() {
        isDarkMode = !isDarkMode
    }

    fun changeCurrency(currency: String) {
        selectedCurrency = currency
    }

    fun exportPdf(context: Context) {
        // Logique d'export PDF
    }

    fun exportExcel(context: Context) {
        // Logique d'export Excel
    }
}