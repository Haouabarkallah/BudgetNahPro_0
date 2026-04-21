package com.budgetnah.pro.presentation.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0012\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\tH\u0007\u001a<\u0010\f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a \u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000eH\u0007\u001a\u0010\u0010\u001b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001c"}, d2 = {"BudgetAlertCard", "", "alert", "Lcom/budgetnah/pro/presentation/dashboard/BudgetAlert;", "DashboardScreen", "viewModel", "Lcom/budgetnah/pro/presentation/dashboard/DashboardViewModel;", "EmptyStateIllustration", "message", "", "SectionHeader", "title", "SmallSummaryCard", "amount", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "color", "Landroidx/compose/ui/graphics/Color;", "modifier", "Landroidx/compose/ui/Modifier;", "SmallSummaryCard-42QJj7c", "(Ljava/lang/String;DLandroidx/compose/ui/graphics/vector/ImageVector;JLandroidx/compose/ui/Modifier;)V", "SummaryCards", "totalBalance", "totalIncome", "totalExpenses", "formatCurrency", "app_debug"})
public final class DashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.presentation.dashboard.DashboardViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SectionHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EmptyStateIllustration(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SummaryCards(double totalBalance, double totalIncome, double totalExpenses) {
    }
    
    private static final java.lang.String formatCurrency(double amount) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void BudgetAlertCard(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.presentation.dashboard.BudgetAlert alert) {
    }
}