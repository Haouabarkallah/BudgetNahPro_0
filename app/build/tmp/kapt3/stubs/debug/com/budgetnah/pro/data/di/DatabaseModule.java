package com.budgetnah.pro.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0010"}, d2 = {"Lcom/budgetnah/pro/data/di/DatabaseModule;", "", "()V", "provideBudgetLimitDao", "Lcom/budgetnah/pro/data/local/dao/BudgetLimitDao;", "database", "Lcom/budgetnah/pro/data/local/database/BudgetNahDatabase;", "provideCategoryDao", "Lcom/budgetnah/pro/data/local/dao/CategoryDao;", "provideDatabase", "context", "Landroid/content/Context;", "provideExpenseDao", "Lcom/budgetnah/pro/data/local/dao/ExpenseDao;", "provideUserDao", "Lcom/budgetnah/pro/data/local/dao/UserDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.budgetnah.pro.data.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.data.local.database.BudgetNahDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.data.local.dao.UserDao provideUserDao(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.data.local.database.BudgetNahDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.data.local.dao.CategoryDao provideCategoryDao(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.data.local.database.BudgetNahDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.data.local.dao.ExpenseDao provideExpenseDao(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.data.local.database.BudgetNahDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.data.local.dao.BudgetLimitDao provideBudgetLimitDao(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.data.local.database.BudgetNahDatabase database) {
        return null;
    }
}