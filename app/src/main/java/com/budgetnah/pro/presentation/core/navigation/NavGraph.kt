package com.budgetnah.pro.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.budgetnah.pro.domain.repository.AuthRepository
import com.budgetnah.pro.presentation.features.auth.AuthScreen
import com.budgetnah.pro.presentation.features.categories.CategoriesScreen
import com.budgetnah.pro.presentation.features.dashboard.DashboardScreen
import com.budgetnah.pro.presentation.features.expenses.AddExpenseScreen
import com.budgetnah.pro.presentation.features.expenses.ExpenseScreen
import com.budgetnah.pro.presentation.features.goals.GoalsScreen
import com.budgetnah.pro.presentation.features.landing.LandingScreen
import com.budgetnah.pro.presentation.features.settings.SettingsScreen
import com.budgetnah.pro.presentation.features.statistics.StatisticsScreen

@Composable
fun BudgetNahNavGraph(
    authRepository: AuthRepository,
    startDestination: String = "landing"
) {
    val start = if (authRepository.getCurrentUser() != null) {
        "dashboard"
    } else {
        "landing"
    }
    val navController = rememberNavController()

    NavHost(navController, startDestination) {

        composable("landing") {
            LandingScreen(onGetStarted = {
                navController.navigate("auth")
            })
        }

        composable("auth") {
            AuthScreen(
                onLoginSuccess = { navController.navigate("dashboard") },
                onRegisterSuccess = { navController.navigate("dashboard") }
            )
        }

        composable("dashboard") {
            DashboardScreen(navController)
        }

        // ✅ LISTE DES DÉPENSES
        composable("expenses") {
            ExpenseScreen(
                onAddClick = {
                    navController.navigate("add_expense")
                }
            )
        }

        // ✅ AJOUT DÉPENSE
        composable("add_expense") {
            AddExpenseScreen(
                onExpenseAdded = {
                    navController.popBackStack()
                }
            )
        }

        // ✅ CATÉGORIES
        composable("categories") {
            CategoriesScreen()
        }

        composable("settings") {
            SettingsScreen()
        }

        composable("statistics") {
            StatisticsScreen()
        }

        composable("goals") {
            GoalsScreen()
        }
    }
}