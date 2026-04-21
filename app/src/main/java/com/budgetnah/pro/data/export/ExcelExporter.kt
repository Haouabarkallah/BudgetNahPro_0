package com.budgetnah.pro.data.export

import android.content.Context
import com.budgetnah.pro.data.local.entities.Expense
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

class ExcelExporter(private val context: Context) {

    fun export(expenses: List<Expense>): File {

        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Expenses")

        // Header
        val header = sheet.createRow(0)
        header.createCell(0).setCellValue("Montant")
        header.createCell(1).setCellValue("Catégorie")
        header.createCell(2).setCellValue("Date")

        expenses.forEachIndexed { index, expense ->
            val row = sheet.createRow(index + 1)

            row.createCell(0).setCellValue(expense.amount)
            row.createCell(1).setCellValue(expense.categoryId.toString())
            row.createCell(2).setCellValue(expense.date.toString())
        }

        val file = File(
            context.getExternalFilesDir(null),
            "expenses.xlsx"
        )

        val output = FileOutputStream(file)
        workbook.write(output)
        output.close()
        workbook.close()

        return file
    }
}