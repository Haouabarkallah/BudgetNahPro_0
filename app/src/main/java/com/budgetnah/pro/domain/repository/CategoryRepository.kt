package com.budgetnah.pro.domain.repository

import com.budgetnah.pro.data.local.entities.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(userId: Int): Flow<List<Category>>
    suspend fun insertCategory(category: Category)
}
