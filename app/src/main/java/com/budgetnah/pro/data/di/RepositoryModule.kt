package com.budgetnah.pro.data.di

import com.budgetnah.pro.data.local.dao.CategoryDao
import com.budgetnah.pro.data.local.dao.ExpenseDao
import com.budgetnah.pro.data.local.repository.AuthRepositoryImpl
import com.budgetnah.pro.data.local.repository.CategoryRepositoryImpl
import com.budgetnah.pro.data.local.repository.ExpenseRepositoryImpl
import com.budgetnah.pro.domain.repository.AuthRepository
import com.budgetnah.pro.domain.repository.CategoryRepository
import com.budgetnah.pro.domain.repository.ExpenseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    companion object {
        @Provides
        @Singleton
        fun provideExpenseRepository(
            dao: ExpenseDao
        ): ExpenseRepository {
            return ExpenseRepositoryImpl(dao)
        }

        @Provides
        @Singleton
        fun provideCategoryRepository(
            dao: CategoryDao
        ): CategoryRepository {
            return CategoryRepositoryImpl(dao)
        }
    }
}

