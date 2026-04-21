package com.example.budgetnah.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetnah.data.local.entity.Category
import com.example.budgetnah.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * State for the delete category confirmation dialog.
 *
 * Also carries [associatedExpenseCount] so the UI can show a contextual
 * warning ("3 expenses will also be deleted") rather than a generic one.
 */
data class DeleteCategoryDialogState(
    val isVisible: Boolean = false,
    val categoryToDelete: Category? = null,
    val associatedExpenseCount: Int = 0
)

class CategoriesViewModel(private val repository: BudgetRepository) : ViewModel() {

    // -------------------------------------------------------------------------
    // Category list — reactive stream from Room
    // -------------------------------------------------------------------------

    val categories: StateFlow<List<Category>> = repository.getAllCategories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    // -------------------------------------------------------------------------
    // New category text field state (hoisted into ViewModel for persistence)
    // -------------------------------------------------------------------------

    private val _newCategoryName = MutableStateFlow("")
    val newCategoryName: StateFlow<String> = _newCategoryName.asStateFlow()

    fun onNewCategoryNameChanged(name: String) {
        _newCategoryName.update { name }
    }

    // -------------------------------------------------------------------------
    // Delete confirmation dialog state
    // -------------------------------------------------------------------------

    private val _deleteDialogState = MutableStateFlow(DeleteCategoryDialogState())
    val deleteDialogState: StateFlow<DeleteCategoryDialogState> = _deleteDialogState.asStateFlow()

    // -------------------------------------------------------------------------
    // CRUD operations
    // -------------------------------------------------------------------------

    fun addCategory() {
        val name = _newCategoryName.value.trim()
        if (name.isBlank()) return

        viewModelScope.launch {
            repository.insertCategory(Category(name = name))
            _newCategoryName.update { "" } // Clear input on success
        }
    }

    /**
     * Before showing the confirmation dialog, we eagerly fetch the expense
     * count so the dialog body can include the count in the warning message.
     */
    fun requestDeleteCategory(category: Category) {
        viewModelScope.launch {
            val count = repository.getExpenseCountForCategory(category.id)
            _deleteDialogState.update {
                DeleteCategoryDialogState(
                    isVisible = true,
                    categoryToDelete = category,
                    associatedExpenseCount = count
                )
            }
        }
    }

    fun dismissDeleteDialog() {
        _deleteDialogState.update { DeleteCategoryDialogState() }
    }

    fun confirmDeleteCategory() {
        val category = _deleteDialogState.value.categoryToDelete ?: return
        viewModelScope.launch {
            repository.deleteCategory(category)
        }
        dismissDeleteDialog()
    }
}