package com.budgetnah.pro.presentation.features.expenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.budgetnah.pro.data.local.entities.Expense
import com.budgetnah.pro.data.ocr.OcrManager
import com.budgetnah.pro.data.voice.VoiceManager
import com.budgetnah.pro.presentation.core.components.PrimaryButton
import com.budgetnah.pro.presentation.core.components.CurrencySelector
import com.budgetnah.pro.presentation.core.components.CategoryDropdown
import com.google.mlkit.vision.common.InputImage
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    viewModel: ExpenseViewModel = hiltViewModel(),
    onExpenseAdded: () -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf("XAF") }
    var selectedCategoryId by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current
    val voiceManager = remember { VoiceManager(context) }
    val userId = 1 // temporaire
    val ocrManager = remember { OcrManager() }
    
    data class ReceiptData(val amount: Double?, val category: String?)
    data class VoiceData(val amount: Double?, val category: String?)

    fun parseReceipt(text: String): ReceiptData {
        // Mock implementation
        return ReceiptData(null, null)
    }

    fun parseVoice(text: String): VoiceData {
        // Mock implementation
        return VoiceData(null, null)
    }

    fun extractAmount(text: String): Double? {
        // Logique simple pour extraire un nombre du texte OCR
        val regex = Regex("""\d+([.,]\d+)?""")
        return regex.find(text)?.value?.replace(',', '.')?.toDoubleOrNull()
    }

    fun processOcr(bitmap: android.graphics.Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        ocrManager.processImage(image) { text ->
            amount = extractAmount(text)?.toString() ?: ""
        }
    }

    var selectedDate by remember { mutableStateOf(System.currentTimeMillis()) }
    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Ajouter une dépense", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // 💰 Amount
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Montant") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                // We need a dummy bitmap for the IconButton for now or remove OCR button for now
                // val bitmap = ...
                IconButton(onClick = {
                    /*
                    val image = InputImage.fromBitmap(bitmap, 0)

                    ocrManager.processImage(image) { text ->

                        val parsed = parseReceipt(text)

                        parsed.amount?.let {
                            amount = it.toString()
                        }

                        parsed.category?.let {
                            // TODO: mapper vers categoryId
                        }

                        note = text
                    }
                    */
                }) {
                    Text("📸")
                }
                IconButton(onClick = {

                    voiceManager.startVoiceInput { text ->

                        val parsed = parseVoice(text)

                        parsed.amount?.let {
                            amount = it.toString()
                        }

                        parsed.category?.let {
                            // TODO mapper categoryId
                        }

                        note = text
                    }

                }) {
                    Text("🎙️")
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 💱 Currency
        CurrencySelector(
            selectedCurrency = selectedCurrency,
            onCurrencySelected = { selectedCurrency = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 📂 Category
        CategoryDropdown(
            onCategorySelected = { categoryId ->
                selectedCategoryId = categoryId
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // datepicker
        OutlinedButton(
            onClick = { showDatePicker = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📅 ${SimpleDateFormat("dd MMM yyyy").format(Date(selectedDate))}")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 📝 Note
        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Note (optionnel)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Enregistrer",
            onClick = {
                val amountValue = amount.toDoubleOrNull()
                if (amountValue == null || selectedCategoryId == null) {
                    return@PrimaryButton
                }

                val expense = Expense(
                    amount = amountValue,
                    categoryId = selectedCategoryId!!,
                    date = selectedDate,
                    userId = userId
                )

                viewModel.addExpense(expense)
                onExpenseAdded()
            }
        )
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedDate = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text("Annuler")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}