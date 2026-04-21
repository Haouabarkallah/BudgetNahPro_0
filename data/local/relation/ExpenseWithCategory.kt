package com.example.budgetnah.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.budgetnah.data.local.entity.Category
import com.example.budgetnah.data.local.entity.Expense

/**
 * POJO that Room populates via a relational query.
 *
 * Room will JOIN the [Expense] row with its corresponding [Category] row
 * using the [Expense.categoryId] → [Category.id] mapping, delivering a
 * fully hydrated object graph to the UI layer.
 */
data class ExpenseWithCategory(
    @Embedded val expense: Expense,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: Category
)