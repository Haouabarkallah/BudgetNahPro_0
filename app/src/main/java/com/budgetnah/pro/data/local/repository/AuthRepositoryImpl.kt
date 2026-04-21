package com.budgetnah.pro.data.local.repository

import com.budgetnah.pro.domain.model.User
import com.budgetnah.pro.data.remote.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import com.budgetnah.pro.domain.repository.AuthRepository
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            val user = getCurrentUser()
            if (user != null) Result.success(user)
            else Result.failure(Exception("Login failed"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(email: String, password: String, fullName: String): Result<User> {
        return try {
            supabase.auth.signUpWith(Email) {
                this.email = email
                this.password = password
                data = buildJsonObject {
                    put("full_name", fullName)
                }
            }
            val user = getCurrentUser()
            if (user != null) Result.success(user)
            else Result.failure(Exception("Registration failed"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUser(): User? {
        val supabaseUser = supabase.auth.currentUserOrNull()
        return if (supabaseUser != null) {
            User(
                id = 1, // Placeholder until local DB integration
                supabaseId = supabaseUser.id,
                email = supabaseUser.email ?: "",
                fullName = "" // Supabase user might have this in user_metadata
            )
        } else null
    }
}