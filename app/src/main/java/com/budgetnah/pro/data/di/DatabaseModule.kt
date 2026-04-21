// data/di/DatabaseModule.kt
package com.budgetnah.pro.data.di

import android.content.Context
import androidx.room.Room
import com.budgetnah.pro.data.local.database.BudgetNahDatabase
import com.budgetnah.pro.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BudgetNahDatabase {
        return Room.databaseBuilder(
            context,
            BudgetNahDatabase::class.java,
            "budgetnah_db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: BudgetNahDatabase): UserDao = database.userDao()

    @Provides
    fun provideCategoryDao(database: BudgetNahDatabase): CategoryDao = database.categoryDao()

    @Provides
    fun provideExpenseDao(database: BudgetNahDatabase): ExpenseDao = database.expenseDao()

    @Provides
    fun provideBudgetLimitDao(database: BudgetNahDatabase): BudgetLimitDao = database.budgetLimitDao()
}