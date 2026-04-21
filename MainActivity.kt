package com.example.budgetnah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.budgetnah.data.local.BudgetDatabase
import com.example.budgetnah.domain.repository.BudgetRepository
import com.example.budgetnah.presentation.ViewModelFactory
import com.example.budgetnah.ui.navigation.BudgetNahApp
import com.example.budgetnah.ui.theme.BudgetNahTheme

/**
 * Single-Activity entry point.
 *
 * Responsibility: Bootstrap the dependency graph (Database → Repository →
 * ViewModelFactory) and hand it to the root Compose tree. Navigation and
 * all UI concerns live below [BudgetNahApp].
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ── Manual DI graph ──────────────────────────────────────────────────
        val database = BudgetDatabase.getDatabase(applicationContext)
        val repository = BudgetRepository(
            expenseDao = database.expenseDao(),
            categoryDao = database.categoryDao()
        )
        val viewModelFactory = ViewModelFactory(repository)
        // ─────────────────────────────────────────────────────────────────────

        setContent {
            BudgetNahTheme {
                BudgetNahApp(factory = viewModelFactory)
            }
        }
    }
}