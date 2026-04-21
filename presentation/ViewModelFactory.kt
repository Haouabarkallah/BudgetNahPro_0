package com.example.budgetnah.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetnah.domain.repository.BudgetRepository
import com.example.budgetnah.presentation.categories.CategoriesViewModel
import com.example.budgetnah.presentation.expenses.ExpensesViewModel

/**
 * Manual dependency injection via [ViewModelProvider.Factory].
 *
 * This factory receives the shared [BudgetRepository] and constructs the
 * correct ViewModel subtype. It's instantiated once in [MainActivity] and
 * passed down to each screen composable.
 *
 * If the project grows significantly, migrate to Hilt for compile-time
 * verified DI with less boilerplate.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BudgetRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ExpensesViewModel::class.java) ->
                ExpensesViewModel(repository) as T

            modelClass.isAssignableFrom(CategoriesViewModel::class.java) ->
                CategoriesViewModel(repository) as T

            else -> throw IllegalArgumentException(
                "Unknown ViewModel class: ${modelClass.name}. " +
                        "Register it in ViewModelFactory."
            )
        }
    }
}