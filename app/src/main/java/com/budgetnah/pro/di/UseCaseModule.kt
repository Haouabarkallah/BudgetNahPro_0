package com.budgetnah.pro.di

import com.budgetnah.pro.domain.repository.AuthRepository
import com.budgetnah.pro.domain.usecase.LoginUseCase
import com.budgetnah.pro.domain.usecase.RegisterUseCase
import com.budgetnah.pro.domain.usecase.dashboard.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase = LoginUseCase(repository)

    @Provides
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase = RegisterUseCase(repository)

    @Provides
    fun provideGetLoggedInUserUseCase(): GetLoggedInUserUseCase = GetLoggedInUserUseCase()

    @Provides
    fun provideGetExpensesForUserUseCase(): GetExpensesForUserUseCase = GetExpensesForUserUseCase()

    @Provides
    fun provideGetBudgetLimitsForUserUseCase(): GetBudgetLimitsForUserUseCase = GetBudgetLimitsForUserUseCase()

    @Provides
    fun provideGetCategoriesForUserUseCase(): GetCategoriesForUserUseCase = GetCategoriesForUserUseCase()
}
