package com.example.budgetnah.ui.screens.expenses.dialogs

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.budgetnah.R
import com.example.budgetnah.data.local.entity.Category
import com.example.budgetnah.presentation.expenses.AddEditExpenseDialogState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

/**
 * Modal dialog for creating or editing an expense.
 *
 * All state is owned by the ViewModel and passed in as parameters — the dialog
 * is a purely stateless composable (except for the dropdown expanded flag,
 * which is purely local UI state).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditExpenseDialog(
    state: AddEditExpenseDialogState,
    categories: List<Category>,
    onAmountChanged: (String) -> Unit,
    onCategorySelected: (Category) -> Unit,
    onDateSelected: (Long) -> Unit,
    onSave: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var dropdownExpanded by remember { mutableStateOf(false) }

    // Build DatePickerDialog lazily; shown when the calendar icon is tapped
    val calendar = remember(state.selectedDateMillis) {
        Calendar.getInstance().apply { timeInMillis = state.selectedDateMillis }
    }

    val datePickerDialog = remember(calendar) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val picked = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth, 0, 0, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                onDateSelected(picked.timeInMillis)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    val titleRes = if (state.expenseToEdit != null) {
        R.string.dialog_edit_expense_title
    } else {
        R.string.dialog_add_expense_title
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(titleRes)) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {

                // ── Amount field ────────────────────────────────────────────
                OutlinedTextField(
                    value = state.amountInput,
                    onValueChange = onAmountChanged,
                    label = { Text(stringResource(R.string.dialog_amount_label)) },
                    placeholder = { Text(stringResource(R.string.dialog_amount_placeholder)) },
                    isError = state.amountError,
                    supportingText = if (state.amountError) {
                        { Text(stringResource(R.string.dialog_amount_error)) }
                    } else null,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // ── Category dropdown ────────────────────────────────────────
                ExposedDropdownMenuBox(
                    expanded = dropdownExpanded,
                    onExpandedChange = { dropdownExpanded = it },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = state.selectedCategory?.name
                            ?: stringResource(R.string.dialog_category_label),
                        onValueChange = {},
                        readOnly = true,
                        isError = state.categoryError,
                        supportingText = if (state.categoryError) {
                            { Text(stringResource(R.string.dialog_category_error)) }
                        } else null,
                        label = { Text(stringResource(R.string.dialog_category_label)) },
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category.name) },
                                onClick = {
                                    onCategorySelected(category)
                                    dropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                // ── Date picker trigger ──────────────────────────────────────
                OutlinedTextField(
                    value = dateFormatter.format(Date(state.selectedDateMillis)),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.dialog_date_label)) },
                    trailingIcon = {
                        IconButton(onClick = { datePickerDialog.show() }) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = stringResource(R.string.dialog_date_label)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onSave) {
                Text(stringResource(R.string.dialog_btn_save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.dialog_btn_cancel))
            }
        }
    )
}