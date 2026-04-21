package com.budgetnah.pro.data.local.repository

import com.budgetnah.pro.data.local.dao.CategoryDao
import com.budgetnah.pro.data.local.entities.Category
import com.budgetnah.pro.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val dao: CategoryDao
) : CategoryRepository {

    override fun getAllCategories(userId: Int): Flow<List<Category>> {
        return dao.getAllCategories(userId)
    }

    override suspend fun insertCategory(category: Category) {
        dao.insertCategory(category)
    }

    suspend fun insertDefaultCategories(userId: Int) {
        val defaultCategories = listOf(
            Category(name = "Food 🍔", userId = userId, color = 0xFFE57373.toInt().toLong(), iconRes = 0),
            Category(name = "Transport 🚗", userId = userId, color = 0xFF64B5F6.toInt().toLong(), iconRes = 0),
            Category(name = "Shopping 🛒", userId = userId, color = 0xFFBA68C8.toInt().toLong(), iconRes = 0),
            Category(name = "Bills 💡", userId = userId, color = 0xFFFFD54F.toInt().toLong(), iconRes = 0)
        )

        defaultCategories.forEach {
            dao.insertCategory(it)
        }
    }
}
