// MainActivity.kt

// MainActivity.kt
package com.budgetnah.pro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.budgetnah.pro.domain.repository.AuthRepository
import com.budgetnah.pro.presentation.core.navigation.BudgetNahNavGraph
import com.budgetnah.pro.presentation.core.theme.BudgetNahProTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetNahProTheme {
                BudgetNahNavGraph(authRepository = authRepository)
            }
        }
    }
}
