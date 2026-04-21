package com.budgetnah.pro.domain.usecase

import com.budgetnah.pro.domain.model.User
import com.budgetnah.pro.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return authRepository.login(email, password)
    }
}
