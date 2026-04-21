package com.example.budgetnah.presentation.expenses

import com.example.budgetnah.data.local.entity.Category
import com.example.budgetnah.data.local.entity.Expense
import com.example.budgetnah.data.local.relation.ExpenseWithCategory

/**
 * Sealed class modelling every possible state of the Expenses screen.
 *
 * Using a sealed class instead of a simple data class gives us exhaustive
 * when-expressions in the UI, making it impossible to accidentally ignore
 * a state transition.
 */
sealed class ExpensesUiState {

    /** Initial state — data is being fetched from Room. */
    object Loading : ExpensesUiState()

    /**
     * Data loaded successfully.
     *
     * @param expenses List of expenses with their associated category.
     * @param categories Full category list, used to populate the add/edit dialog dropdown.
     */
    data class Success(
        val expenses: List<ExpenseWithCategory>,
        val categories: List<Category>
    ) : ExpensesUiState()

    /** An unrecoverable error occurred. */
    data class Error(val message: String) : ExpensesUiState()
}

/**
 * State for the Add/Edit Expense dialog.
 *
 * Kept separate from [ExpensesUiState] so the dialog can be shown/hidden
 * independently of the list state.
 */
data class AddEditExpenseDialogState(
    val isVisible: Boolean = false,
    /** Null means we are adding; non-null means we are editing. */
    val expenseToEdit: Expense? = null,
    val amountInput: String = "",
    val selectedCategory: Category? = null,
    val selectedDateMillis: Long = System.currentTimeMillis(),
    val amountError: Boolean = false,
    val categoryError: Boolean = false
)

/**
 * State for the delete-confirmation dialog.
 */
data class DeleteExpenseDialogState(
    val isVisible: Boolean = false,
    val expenseToDelete: ExpenseWithCategory? = null
)