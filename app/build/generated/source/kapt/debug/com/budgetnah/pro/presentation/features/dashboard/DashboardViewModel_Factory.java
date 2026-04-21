package com.budgetnah.pro.presentation.features.dashboard;

import com.budgetnah.pro.domain.usecase.dashboard.GetBudgetLimitsForUserUseCase;
import com.budgetnah.pro.domain.usecase.dashboard.GetCategoriesForUserUseCase;
import com.budgetnah.pro.domain.usecase.dashboard.GetExpensesForUserUseCase;
import com.budgetnah.pro.domain.usecase.dashboard.GetLoggedInUserUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "cast"
})
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<GetLoggedInUserUseCase> getUserUseCaseProvider;

  private final Provider<GetExpensesForUserUseCase> getExpensesUseCaseProvider;

  private final Provider<GetBudgetLimitsForUserUseCase> getBudgetLimitsUseCaseProvider;

  private final Provider<GetCategoriesForUserUseCase> getCategoriesUseCaseProvider;

  public DashboardViewModel_Factory(Provider<GetLoggedInUserUseCase> getUserUseCaseProvider,
      Provider<GetExpensesForUserUseCase> getExpensesUseCaseProvider,
      Provider<GetBudgetLimitsForUserUseCase> getBudgetLimitsUseCaseProvider,
      Provider<GetCategoriesForUserUseCase> getCategoriesUseCaseProvider) {
    this.getUserUseCaseProvider = getUserUseCaseProvider;
    this.getExpensesUseCaseProvider = getExpensesUseCaseProvider;
    this.getBudgetLimitsUseCaseProvider = getBudgetLimitsUseCaseProvider;
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(getUserUseCaseProvider.get(), getExpensesUseCaseProvider.get(), getBudgetLimitsUseCaseProvider.get(), getCategoriesUseCaseProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<GetLoggedInUserUseCase> getUserUseCaseProvider,
      Provider<GetExpensesForUserUseCase> getExpensesUseCaseProvider,
      Provider<GetBudgetLimitsForUserUseCase> getBudgetLimitsUseCaseProvider,
      Provider<GetCategoriesForUserUseCase> getCategoriesUseCaseProvider) {
    return new DashboardViewModel_Factory(getUserUseCaseProvider, getExpensesUseCaseProvider, getBudgetLimitsUseCaseProvider, getCategoriesUseCaseProvider);
  }

  public static DashboardViewModel newInstance(GetLoggedInUserUseCase getUserUseCase,
      GetExpensesForUserUseCase getExpensesUseCase,
      GetBudgetLimitsForUserUseCase getBudgetLimitsUseCase,
      GetCategoriesForUserUseCase getCategoriesUseCase) {
    return new DashboardViewModel(getUserUseCase, getExpensesUseCase, getBudgetLimitsUseCase, getCategoriesUseCase);
  }
}
