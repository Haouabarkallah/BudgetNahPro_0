package com.example.budgetnah.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.budgetnah.data.local.dao.CategoryDao
import com.example.budgetnah.data.local.dao.ExpenseDao
import com.example.budgetnah.data.local.entity.Category
import com.example.budgetnah.data.local.entity.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Singleton Room database.
 *
 * Version history:
 *  - v1: Initial schema (categories + expenses tables)
 *
 * Pre-population is handled by [PrepopulateCallback] which inserts the five
 * default categories the first time the database is created.
 */
@Database(
    entities = [Category::class, Expense::class],
    version = 1,
    exportSchema = true
)
abstract class BudgetDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao

    companion object {

        @Volatile
        private var INSTANCE: BudgetDatabase? = null

        private val DEFAULT_CATEGORIES = listOf(
            "Food",
            "Transport",
            "Bills",
            "Shopping",
            "Entertainment"
        )

        fun getDatabase(context: Context): BudgetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BudgetDatabase::class.java,
                    "budget_nah_database"
                )
                    .addCallback(PrepopulateCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        /**
         * RoomDatabase.Callback that runs once when the database is first created.
         * We insert the default categories here so the user has a useful starting
         * state without any extra setup.
         */
        private class PrepopulateCallback : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Run on IO dispatcher — we're inside a callback, not a coroutine scope
                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE?.categoryDao()?.let { dao ->
                        DEFAULT_CATEGORIES.forEach { name ->
                            dao.insertCategory(Category(name = name))
                        }
                    }
                }
            }
        }
    }
}