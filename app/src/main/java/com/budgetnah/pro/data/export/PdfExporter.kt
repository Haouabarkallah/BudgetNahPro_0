package com.budgetnah.pro.data.export

import android.content.Context
import com.budgetnah.pro.data.local.entities.Expense
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import java.io.File

class PdfExporter(private val context: Context) {

    fun export(expenses: List<Expense>): File {

        val file = File(
            context.getExternalFilesDir(null),
            "expenses.pdf"
        )

        val writer = PdfWriter(file)
        val pdf = PdfDocument(writer)
        val document = Document(pdf)

        document.add(Paragraph("BudgetNah - Rapport des dépenses\n"))

        expenses.forEach {
            document.add(
                Paragraph(
                    "💰 ${it.amount} | 📂 ${it.categoryId} | 📅 ${it.date}"
                )
            )
        }

        document.close()

        return file
    }
}