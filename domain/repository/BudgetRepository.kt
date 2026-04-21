package com.example.budgetnah.domain.repository

import com.example.budgetnah.data.local.dao.CategoryDao
import com.example.budgetnah.data.local.dao.ExpenseDao
import com.example.budgetnah.data.local.entity.Category
import com.example.budgetnah.data.local.entity.Expense
import com.example.budgetnah.data.local.relation.ExpenseWithCategory
import kotlinx.coroutines.flow.Flow

/**
 * Single source of truth for all budget data.
 *
 * The repository acts as a mediator between the data sources (Room DAOs)
 * and the ViewModels. It centralises:
 *  - Which DAO to call for each operation.
 *  - Any data transformations before the data reaches the UI.
 *
 * Currently backed by a local Room database only. Future extensions could
 * add a remote data source here without touching the ViewModel layer.
 */
class BudgetRepository(
    private val expenseDao: ExpenseDao,
    private val categoryDao: CategoryDao
) {

    // -------------------------------------------------------------------------
    // Expenses
    // -------------------------------------------------------------------------

    /** Live stream of all expenses, each paired with its category. Ordered newest first. */
    fun getAllExpensesWithCategories(): Flow<List<ExpenseWithCategory>> =
        expenseDao.getAllExpensesWithCategories()

    suspend fun insertExpense(expense: Expense): Long =
        expenseDao.insertExpense(expense)

    suspend fun updateExpense(expense: Expense) =
        expenseDao.updateExpense(expense)

    suspend fun deleteExpense(expense: Expense) =
        expenseDao.deleteExpense(expense)

    // -------------------------------------------------------------------------
    // Categories
    // -------------------------------------------------------------------------

    /** Live stream of all categories, ordered alphabetically. */
    fun getAllCategories(): Flow<List<Category>> =
        categoryDao.getAllCategories()

    suspend fun insertCategory(category: Category): Long =
        categoryDao.insertCategory(category)

    suspend fun updateCategory(category: Category) =
        categoryDao.updateCategory(category)

    suspend fun deleteCategory(category: Category) =
        categoryDao.deleteCategory(category)

    /**
     * Returns how many expenses belong to [categoryId].
     * Used by the UI to warn the user that deletion will cascade.
     */
    suspend fun getExpenseCountForCategory(categoryId: Int): Int =
        categoryDao.getExpenseCountForCategory(categoryId)
}