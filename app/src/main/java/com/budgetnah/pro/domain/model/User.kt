package com.budgetnah.pro.domain.model

data class User(
    val id: Int,
    val supabaseId: String,
    val email: String,
    val fullName: String
)
