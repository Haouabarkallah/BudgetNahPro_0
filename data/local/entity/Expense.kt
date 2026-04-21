package com.example.budgetnah.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Room entity representing a single expense record.
 *
 * - [categoryId] is a foreign key referencing [Category.id].
 * - ON DELETE CASCADE: if the parent [Category] is deleted, all associated
 *   [Expense] rows are automatically deleted by the database engine.
 * - An index is added on [categoryId] for efficient JOIN queries.
 */
@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["categoryId"])]
)
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val categoryId: Int,
    val date: Long // Unix timestamp in milliseconds
)