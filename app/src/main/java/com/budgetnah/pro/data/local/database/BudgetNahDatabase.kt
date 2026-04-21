package com.budgetnah.pro.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.budgetnah.pro.data.local.dao.BudgetLimitDao
import com.budgetnah.pro.data.local.dao.CategoryDao
import com.budgetnah.pro.data.local.dao.ExpenseDao
import com.budgetnah.pro.data.local.dao.UserDao
import com.budgetnah.pro.data.local.entities.BudgetLimit
import com.budgetnah.pro.data.local.entities.Category
import com.budgetnah.pro.data.local.entities.Expense
import com.budgetnah.pro.data.local.entities.User

@Database(
    entities = [Expense::class, Category::class, User::class, BudgetLimit::class],
    version = 1,
    exportSchema = false
)
abstract class BudgetNahDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun budgetLimitDao(): BudgetLimitDao

    companion object {
        @Volatile
        private var INSTANCE: BudgetNahDatabase? = null

        fun getInstance(context: Context): BudgetNahDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BudgetNahDatabase::class.java,
                    "budgetnah_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
