package com.example.budgetnah.presentation.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetnah.data.local.entity.Category
import com.example.budgetnah.data.local.entity.Expense
import com.example.budgetnah.data.local.relation.ExpenseWithCategory
import com.example.budgetnah.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExpensesViewModel(private val repository: BudgetRepository) : ViewModel() {

    // -------------------------------------------------------------------------
    // Main UI state — combines expenses + categories into a single sealed class
    // -------------------------------------------------------------------------

    val uiState: StateFlow<ExpensesUiState> = combine(
        repository.getAllExpensesWithCategories(),
        repository.getAllCategories()
    ) { expenses, categories ->
        ExpensesUiState.Success(
            expenses = expenses,
            categories = categories
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ExpensesUiState.Loading
    )

    // -------------------------------------------------------------------------
    // Add/Edit dialog state
    // -------------------------------------------------------------------------

    private val _dialogState = MutableStateFlow(AddEditExpenseDialogState())
    val dialogState: StateFlow<AddEditExpenseDialogState> = _dialogState.asStateFlow()

    // -------------------------------------------------------------------------
    // Delete confirmation dialog state
    // -------------------------------------------------------------------------

    private val _deleteDialogState = MutableStateFlow(DeleteExpenseDialogState())
    val deleteDialogState: StateFlow<DeleteExpenseDialogState> = _deleteDialogState.asStateFlow()

    // -------------------------------------------------------------------------
    // Dialog actions
    // -------------------------------------------------------------------------

    /** Opens the dialog in "add" mode. */
    fun showAddDialog() {
        _dialogState.update {
            AddEditExpenseDialogState(
                isVisible = true,
                selectedDateMillis = System.currentTimeMillis()
            )
        }
    }

    /** Opens the dialog pre-populated with the expense being edited. */
    fun showEditDialog(expenseWithCategory: ExpenseWithCategory) {
        _dialogState.update {
            AddEditExpenseDialogState(
                isVisible = true,
                expenseToEdit = expenseWithCategory.expense,
                amountInput = expenseWithCategory.expense.amount.toString(),
                selectedCategory = expenseWithCategory.category,
                selectedDateMillis = expenseWithCategory.expense.date
            )
        }
    }

    fun dismissDialog() {
        _dialogState.update { AddEditExpenseDialogState() }
    }

    fun onAmountChanged(value: String) {
        _dialogState.update { it.copy(amountInput = value, amountError = false) }
    }

    fun onCategorySelected(category: Category) {
        _dialogState.update { it.copy(selectedCategory = category, categoryError = false) }
    }

    fun onDateSelected(millis: Long) {
        _dialogState.update { it.copy(selectedDateMillis = millis) }
    }

    // -------------------------------------------------------------------------
    // CRUD operations
    // -------------------------------------------------------------------------

    /**
     * Validates the dialog inputs and either inserts or updates an expense.
     * Returns true if the save succeeded (so the caller can close the dialog).
     */
    fun saveExpense(): Boolean {
        val state = _dialogState.value
        val amount = state.amountInput.toDoubleOrNull()
        val category = state.selectedCategory

        val amountError = amount == null || amount <= 0.0
        val categoryError = category == null

        if (amountError || categoryError) {
            _dialogState.update {
                it.copy(amountError = amountError, categoryError = categoryError)
            }
            return false
        }

        viewModelScope.launch {
            if (state.expenseToEdit != null) {
                // Editing an existing expense
                repository.updateExpense(
                    state.expenseToEdit.copy(
                        amount = amount!!,
                        categoryId = category!!.id,
                        date = state.selectedDateMillis
                    )
                )
            } else {
                // Adding a new expense
                repository.insertExpense(
                    Expense(
                        amount = amount!!,
                        categoryId = category!!.id,
                        date = state.selectedDateMillis
                    )
                )
            }
        }
        return true
    }

    /** Shows the delete confirmation dialog for a given expense. */
    fun requestDeleteExpense(expenseWithCategory: ExpenseWithCategory) {
        _deleteDialogState.update {
            DeleteExpenseDialogState(isVisible = true, expenseToDelete = expenseWithCategory)
        }
    }

    fun dismissDeleteDialog() {
        _deleteDialogState.update { DeleteExpenseDialogState() }
    }

    /** Performs the actual deletion after user confirmation. */
    fun confirmDeleteExpense() {
        val expense = _deleteDialogState.value.expenseToDelete?.expense ?: return
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
        dismissDeleteDialog()
    }
}