// data/local/entities/User.kt
package com.budgetnah.pro.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val passwordHash: String, // in real app, hash with bcrypt
    val fullName: String,
    val isLoggedIn: Boolean = false
)