package com.budgetnah.pro.domain.usecase

import com.budgetnah.pro.domain.model.User
import com.budgetnah.pro.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String, fullName: String): Result<User> {
        return repository.register(email, password, fullName)
    }
}
