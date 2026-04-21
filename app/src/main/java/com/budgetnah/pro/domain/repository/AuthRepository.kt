package com.budgetnah.pro.domain.repository

import com.budgetnah.pro.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(email: String, password: String, fullName: String): Result<User>
    fun getCurrentUser(): User?
}