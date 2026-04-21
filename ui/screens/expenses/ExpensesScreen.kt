package com.example.budgetnah.ui.screens.expenses

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ElectricalServices
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.budgetnah.R
import com.example.budgetnah.data.local.relation.ExpenseWithCategory
import com.example.budgetnah.presentation.expenses.DeleteExpenseDialogState
import com.example.budgetnah.presentation.expenses.ExpensesUiState
import com.example.budgetnah.presentation.expenses.ExpensesViewModel
import com.example.budgetnah.ui.screens.expenses.dialogs.AddEditExpenseDialog
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

// ---------------------------------------------------------------------------
// Date / currency formatters (created once, reused across recompositions)
// ---------------------------------------------------------------------------

private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
private val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

// ---------------------------------------------------------------------------
// Root screen composable
// ---------------------------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    viewModel: ExpensesViewModel,
    contentPadding: PaddingValues = PaddingValues()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()
    val deleteDialogState by viewModel.deleteDialogState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.expenses_screen_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::showAddDialog,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_expense_fab_description),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { scaffoldPadding ->

        // Combine the scaffold inner padding with the bottom nav padding
        val combinedPadding = PaddingValues(
            top = scaffoldPadding.calculateTopPadding(),
            bottom = maxOf(
                scaffoldPadding.calculateBottomPadding(),
                contentPadding.calculateBottomPadding()
            )
        )

        when (val state = uiState) {
            is ExpensesUiState.Loading -> LoadingContent(combinedPadding)
            is ExpensesUiState.Error -> ErrorContent(state.message, combinedPadding)
            is ExpensesUiState.Success -> {
                if (state.expenses.isEmpty()) {
                    EmptyContent(combinedPadding)
                } else {
                    ExpensesList(
                        expenses = state.expenses,
                        contentPadding = combinedPadding,
                        onExpenseClick = viewModel::showEditDialog,
                        onDeleteRequest = viewModel::requestDeleteExpense
                    )
                }
            }
        }

        // Add/Edit dialog
        if (dialogState.isVisible) {
            val successState = uiState as? ExpensesUiState.Success
            AddEditExpenseDialog(
                state = dialogState,
                categories = successState?.categories ?: emptyList(),
                onAmountChanged = viewModel::onAmountChanged,
                onCategorySelected = viewModel::onCategorySelected,
                onDateSelected = viewModel::onDateSelected,
                onSave = {
                    if (viewModel.saveExpense()) viewModel.dismissDialog()
                },
                onDismiss = viewModel::dismissDialog
            )
        }

        // Delete confirmation dialog
        if (deleteDialogState.isVisible) {
            DeleteExpenseDialog(
                state = deleteDialogState,
                onConfirm = viewModel::confirmDeleteExpense,
                onDismiss = viewModel::dismissDeleteDialog
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Content variants
// ---------------------------------------------------------------------------

@Composable
private fun LoadingContent(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        // A lightweight pulse indicator — no dependency on Accompanist needed
        Text(
            text = "Loading…",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ErrorContent(message: String, padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun EmptyContent(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Inbox,
            contentDescription = null,
            modifier = Modifier.size(72.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.expenses_empty_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.expenses_empty_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ExpensesList(
    expenses: List<ExpenseWithCategory>,
    contentPadding: PaddingValues,
    onExpenseClick: (ExpenseWithCategory) -> Unit,
    onDeleteRequest: (ExpenseWithCategory) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding() + 8.dp,
            bottom = contentPadding.calculateBottomPadding() + 8.dp,
            start = 16.dp,
            end = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = expenses,
            key = { it.expense.id }
        ) { expenseWithCategory ->
            SwipeableExpenseItem(
                expenseWithCategory = expenseWithCategory,
                onClick = { onExpenseClick(expenseWithCategory) },
                onDeleteRequest = { onDeleteRequest(expenseWithCategory) }
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Swipeable expense row
// ---------------------------------------------------------------------------

/**
 * Wraps [ExpenseItem] in a horizontal drag gesture that reveals a delete
 * affordance. Dragging past 120dp triggers the delete request automatically.
 */
@Composable
private fun SwipeableExpenseItem(
    expenseWithCategory: ExpenseWithCategory,
    onClick: () -> Unit,
    onDeleteRequest: () -> Unit
) {
    val swipeThreshold = 120f
    var offsetX by remember { mutableFloatStateOf(0f) }
    val draggableState = rememberDraggableState { delta ->
        // Only allow left swipe (negative delta)
        offsetX = (offsetX + delta).coerceIn(-swipeThreshold * 1.5f, 0f)
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        // Delete background — visible as the card slides left
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = MaterialTheme.colorScheme.errorContainer,
                    shape = MaterialTheme.shapes.medium
                ),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.expense_delete_icon_description),
                tint = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.padding(end = 20.dp)
            )
        }

        // Foreground card
        ExpenseItem(
            expenseWithCategory = expenseWithCategory,
            onClick = onClick,
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStopped = {
                        if (offsetX <= -swipeThreshold) {
                            onDeleteRequest()
                        }
                        offsetX = 0f // Snap back
                    }
                )
        )
    }
}

// ---------------------------------------------------------------------------
// Single expense row card
// ---------------------------------------------------------------------------

@Composable
private fun ExpenseItem(
    expenseWithCategory: ExpenseWithCategory,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Category icon avatar
            CategoryIcon(categoryName = expenseWithCategory.category.name)

            Spacer(modifier = Modifier.width(16.dp))

            // Amount + category
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = currencyFormatter.format(expenseWithCategory.expense.amount),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = expenseWithCategory.category.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Date
            Text(
                text = dateFormatter.format(Date(expenseWithCategory.expense.date)),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Category icon helper
// ---------------------------------------------------------------------------

@Composable
private fun CategoryIcon(categoryName: String) {
    val icon: ImageVector = when (categoryName.lowercase()) {
        "food" -> Icons.Default.Restaurant
        "transport" -> Icons.Default.DirectionsCar
        "bills" -> Icons.Default.ElectricalServices
        "entertainment" -> Icons.Default.Movie
        "shopping" -> Icons.Default.ShoppingCart
        else -> Icons.Default.ShoppingCart
    }

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.category_icon_description),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(20.dp)
        )
    }
}

// ---------------------------------------------------------------------------
// Delete confirmation dialog
// ---------------------------------------------------------------------------

@Composable
private fun DeleteExpenseDialog(
    state: DeleteExpenseDialogState,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.confirm_delete_expense_title)) },
        text = { Text(stringResource(R.string.confirm_delete_expense_message)) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = stringResource(R.string.confirm_delete_btn),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.confirm_cancel_btn))
            }
        }
    )
}