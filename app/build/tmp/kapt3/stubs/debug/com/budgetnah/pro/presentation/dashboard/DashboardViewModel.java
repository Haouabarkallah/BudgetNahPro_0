package com.budgetnah.pro.presentation.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u000e\u0010\u0017\u001a\u00020\u0018H\u0082@\u00a2\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u0018H\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001b"}, d2 = {"Lcom/budgetnah/pro/presentation/dashboard/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "getUserUseCase", "Lcom/budgetnah/pro/domain/usecase/dashboard/GetLoggedInUserUseCase;", "getExpensesUseCase", "Lcom/budgetnah/pro/domain/usecase/dashboard/GetExpensesForUserUseCase;", "getBudgetLimitsUseCase", "Lcom/budgetnah/pro/domain/usecase/dashboard/GetBudgetLimitsForUserUseCase;", "getCategoriesUseCase", "Lcom/budgetnah/pro/domain/usecase/dashboard/GetCategoriesForUserUseCase;", "(Lcom/budgetnah/pro/domain/usecase/dashboard/GetLoggedInUserUseCase;Lcom/budgetnah/pro/domain/usecase/dashboard/GetExpensesForUserUseCase;Lcom/budgetnah/pro/domain/usecase/dashboard/GetBudgetLimitsForUserUseCase;Lcom/budgetnah/pro/domain/usecase/dashboard/GetCategoriesForUserUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/budgetnah/pro/presentation/dashboard/DashboardUiState;", "currentUserId", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "getEndOfMonth", "Ljava/util/Date;", "getStartOfMonth", "loadDashboardData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadUserAndData", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetnah.pro.domain.usecase.dashboard.GetLoggedInUserUseCase getUserUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetnah.pro.domain.usecase.dashboard.GetExpensesForUserUseCase getExpensesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetnah.pro.domain.usecase.dashboard.GetBudgetLimitsForUserUseCase getBudgetLimitsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetnah.pro.domain.usecase.dashboard.GetCategoriesForUserUseCase getCategoriesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.budgetnah.pro.presentation.dashboard.DashboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.budgetnah.pro.presentation.dashboard.DashboardUiState> uiState = null;
    private int currentUserId = -1;
    
    @javax.inject.Inject()
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.domain.usecase.dashboard.GetLoggedInUserUseCase getUserUseCase, @org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.domain.usecase.dashboard.GetExpensesForUserUseCase getExpensesUseCase, @org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.domain.usecase.dashboard.GetBudgetLimitsForUserUseCase getBudgetLimitsUseCase, @org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.domain.usecase.dashboard.GetCategoriesForUserUseCase getCategoriesUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.budgetnah.pro.presentation.dashboard.DashboardUiState> getUiState() {
        return null;
    }
    
    private final void loadUserAndData() {
    }
    
    private final java.lang.Object loadDashboardData(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.util.Date getStartOfMonth() {
        return null;
    }
    
    private final java.util.Date getEndOfMonth() {
        return null;
    }
}