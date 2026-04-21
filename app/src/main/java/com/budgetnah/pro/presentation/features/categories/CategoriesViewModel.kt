package com.budgetnah.pro.presentation.features.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgetnah.pro.data.local.dao.CategoryDao
import com.budgetnah.pro.data.local.entities.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val dao: CategoryDao
) : ViewModel() {

    var categories by mutableStateOf<List<Category>>(emptyList())
        private set

    init {
        loadCategories(1)
    }

    fun loadCategories(userId: Int) {
        viewModelScope.launch {
            dao.getUserCategories(userId).collect {
                categories = it
            }
        }
    }

    fun addCategory(name: String) {
        viewModelScope.launch {
            dao.insertCategory(
                Category(
                    name = name,
                    userId = 1,
                    iconRes = 0,
                    color = 0xFF808080
                )
            )
        }
    }
}