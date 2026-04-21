// data/local/entities/Category.kt
package com.budgetnah.pro.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val iconRes: Int, // resource id for material icon
    val color: Long, // ARGB
    val userId: Int = 0
)