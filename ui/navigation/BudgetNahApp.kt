package com.example.budgetnah.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.budgetnah.R
import com.example.budgetnah.presentation.ViewModelFactory
import com.example.budgetnah.presentation.categories.CategoriesViewModel
import com.example.budgetnah.presentation.expenses.ExpensesViewModel
import com.example.budgetnah.ui.screens.categories.CategoriesScreen
import com.example.budgetnah.ui.screens.expenses.ExpensesScreen

// ---------------------------------------------------------------------------
// Route constants
// ---------------------------------------------------------------------------

private object Routes {
    const val EXPENSES = "expenses"
    const val CATEGORIES = "categories"
}

// ---------------------------------------------------------------------------
// Navigation item descriptor
// ---------------------------------------------------------------------------

private data class BottomNavItem(
    val route: String,
    val labelRes: Int,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

private val bottomNavItems = listOf(
    BottomNavItem(
        route = Routes.EXPENSES,
        labelRes = R.string.nav_expenses,
        icon = Icons.Default.List
    ),
    BottomNavItem(
        route = Routes.CATEGORIES,
        labelRes = R.string.nav_categories,
        icon = Icons.Default.Settings
    )
)

// ---------------------------------------------------------------------------
// 
// ---------------------------------------------------------------------------

/**
 * Root composable that sets up the NavController and the bottom navigation
 * Scaffold. Each tab hosts its own screen composable.
 *
 * @param factory The shared [ViewModelFactory] that wires the repository into
 *                each ViewModel.
 */
@Composable
fun BudgetNahApp(factory: ViewModelFactory) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = stringResource(item.labelRes)
                            )
                        },
                        label = { Text(stringResource(item.labelRes)) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                // Pop up to the start destination so back-stack stays clean
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.EXPENSES
        ) {
            composable(Routes.EXPENSES) {
                val vm: ExpensesViewModel = viewModel(factory = factory)
                ExpensesScreen(
                    viewModel = vm,
                    contentPadding = innerPadding
                )
            }
            composable(Routes.CATEGORIES) {
                val vm: CategoriesViewModel = viewModel(factory = factory)
                CategoriesScreen(
                    viewModel = vm,
                    contentPadding = innerPadding
                )
            }
        }
    }
}