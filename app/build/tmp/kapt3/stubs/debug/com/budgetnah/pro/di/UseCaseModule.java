package com.budgetnah.pro.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/budgetnah/pro/di/UseCaseModule;", "", "()V", "provideGetBudgetLimitsForUserUseCase", "Lcom/budgetnah/pro/domain/usecase/dashboard/GetBudgetLimitsForUserUseCase;", "provideGetCategoriesForUserUseCase", "Lcom/budgetnah/pro/domain/usecase/dashboard/GetCategoriesForUserUseCase;", "provideGetExpensesForUserUseCase", "Lcom/budgetnah/pro/domain/usecase/dashboard/GetExpensesForUserUseCase;", "provideGetLoggedInUserUseCase", "Lcom/budgetnah/pro/domain/usecase/dashboard/GetLoggedInUserUseCase;", "provideLoginUseCase", "Lcom/budgetnah/pro/domain/usecase/LoginUseCase;", "repository", "Lcom/budgetnah/pro/domain/repository/AuthRepository;", "provideRegisterUseCase", "Lcom/budgetnah/pro/domain/usecase/RegisterUseCase;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class UseCaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.budgetnah.pro.di.UseCaseModule INSTANCE = null;
    
    private UseCaseModule() {
        super();
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.domain.usecase.LoginUseCase provideLoginUseCase(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.domain.repository.AuthRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.domain.usecase.RegisterUseCase provideRegisterUseCase(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.domain.repository.AuthRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.domain.usecase.dashboard.GetLoggedInUserUseCase provideGetLoggedInUserUseCase() {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.domain.usecase.dashboard.GetExpensesForUserUseCase provideGetExpensesForUserUseCase() {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.domain.usecase.dashboard.GetBudgetLimitsForUserUseCase provideGetBudgetLimitsForUserUseCase() {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.budgetnah.pro.domain.usecase.dashboard.GetCategoriesForUserUseCase provideGetCategoriesForUserUseCase() {
        return null;
    }
}