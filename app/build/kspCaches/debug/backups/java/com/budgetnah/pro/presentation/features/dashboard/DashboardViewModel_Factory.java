package com.budgetnah.pro.presentation.features.dashboard;

import com.budgetnah.pro.domain.repository.ExpenseRepository;
import com.budgetnah.pro.domain.usecase.dashboard.GetBudgetLimitsForUserUseCase;
import com.budgetnah.pro.domain.usecase.dashboard.GetCategoriesForUserUseCase;
import com.budgetnah.pro.domain.usecase.dashboard.GetExpensesForUserUseCase;
import com.budgetnah.pro.domain.usecase.dashboard.GetLoggedInUserUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<GetLoggedInUserUseCase> getUserUseCaseProvider;

  private final Provider<GetExpensesForUserUseCase> getExpensesUseCaseProvider;

  private final Provider<GetBudgetLimitsForUserUseCase> getBudgetLimitsUseCaseProvider;

  private final Provider<GetCategoriesForUserUseCase> getCategoriesUseCaseProvider;

  private final Provider<ExpenseRepository> repositoryProvider;

  private DashboardViewModel_Factory(Provider<GetLoggedInUserUseCase> getUserUseCaseProvider,
      Provider<GetExpensesForUserUseCase> getExpensesUseCaseProvider,
      Provider<GetBudgetLimitsForUserUseCase> getBudgetLimitsUseCaseProvider,
      Provider<GetCategoriesForUserUseCase> getCategoriesUseCaseProvider,
      Provider<ExpenseRepository> repositoryProvider) {
    this.getUserUseCaseProvider = getUserUseCaseProvider;
    this.getExpensesUseCaseProvider = getExpensesUseCaseProvider;
    this.getBudgetLimitsUseCaseProvider = getBudgetLimitsUseCaseProvider;
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(getUserUseCaseProvider.get(), getExpensesUseCaseProvider.get(), getBudgetLimitsUseCaseProvider.get(), getCategoriesUseCaseProvider.get(), repositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<GetLoggedInUserUseCase> getUserUseCaseProvider,
      Provider<GetExpensesForUserUseCase> getExpensesUseCaseProvider,
      Provider<GetBudgetLimitsForUserUseCase> getBudgetLimitsUseCaseProvider,
      Provider<GetCategoriesForUserUseCase> getCategoriesUseCaseProvider,
      Provider<ExpenseRepository> repositoryProvider) {
    return new DashboardViewModel_Factory(getUserUseCaseProvider, getExpensesUseCaseProvider, getBudgetLimitsUseCaseProvider, getCategoriesUseCaseProvider, repositoryProvider);
  }

  public static DashboardViewModel newInstance(GetLoggedInUserUseCase getUserUseCase,
      GetExpensesForUserUseCase getExpensesUseCase,
      GetBudgetLimitsForUserUseCase getBudgetLimitsUseCase,
      GetCategoriesForUserUseCase getCategoriesUseCase, ExpenseRepository repository) {
    return new DashboardViewModel(getUserUseCase, getExpensesUseCase, getBudgetLimitsUseCase, getCategoriesUseCase, repository);
  }
}
