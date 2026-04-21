package com.budgetnah.pro.presentation.features.settings

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.budgetnah.pro.presentation.core.components.CurrencySelector
import com.budgetnah.pro.presentation.core.components.PrimaryButton

@Composable
fun SettingsScreen(
    viewModel: SettingViewModel = hiltViewModel()
) {

    Column(modifier = Modifier.padding(16.dp)) {

        Text("⚙️ Paramètres", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // 🌙 Theme
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mode sombre")
            Switch(
                checked = viewModel.isDarkMode,
                onCheckedChange = { viewModel.toggleTheme() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 💱 Currency
        Text("Devise")

        CurrencySelector(
            selectedCurrency = viewModel.selectedCurrency,
            onCurrencySelected = {
                viewModel.changeCurrency(it)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        val context = LocalContext.current

        PrimaryButton(
            text = "Exporter PDF",
            onClick = {
                viewModel.exportPdf(context)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryButton(
            text = "Exporter Excel",
            onClick = {
                viewModel.exportExcel(context)
            }
        )
    }
}
