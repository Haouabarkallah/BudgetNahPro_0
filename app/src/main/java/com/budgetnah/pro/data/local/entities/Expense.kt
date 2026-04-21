// data/local/entities/Expense.kt
package com.budgetnah.pro.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "expenses",
    indices = [Index("userId"), Index("date"), Index("categoryId")],
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Expense(
    @PrimaryKey val id: String = UUID.randomUUID().toString(), // 🔥 important
    val amount: Double,
    val categoryId: Int,
    val date: Long, // ✅ FIX
    val userId: Int,
    val synced: Boolean = false // 🔥 état sync
)