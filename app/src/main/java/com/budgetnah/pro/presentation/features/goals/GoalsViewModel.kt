package com.budgetnah.pro.presentation.features.goals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor() : ViewModel() {

    var goals by mutableStateOf(
        listOf(
            Goal("Économiser 100k", 0.4f),
            Goal("Voyage ✈️", 0.7f)
        )
    )
        private set

    fun addGoal(goal: Goal) {
        goals = goals + goal
    }
}

data class Goal(
    val title: String,
    val progress: Float
)