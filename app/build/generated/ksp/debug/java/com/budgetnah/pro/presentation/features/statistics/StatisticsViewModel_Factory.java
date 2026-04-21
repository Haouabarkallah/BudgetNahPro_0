package com.budgetnah.pro.presentation.features.statistics;

import com.budgetnah.pro.data.local.dao.CategoryDao;
import com.budgetnah.pro.data.local.dao.ExpenseDao;
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
public final class StatisticsViewModel_Factory implements Factory<StatisticsViewModel> {
  private final Provider<ExpenseDao> expenseDaoProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private StatisticsViewModel_Factory(Provider<ExpenseDao> expenseDaoProvider,
      Provider<CategoryDao> categoryDaoProvider) {
    this.expenseDaoProvider = expenseDaoProvider;
    this.categoryDaoProvider = categoryDaoProvider;
  }

  @Override
  public StatisticsViewModel get() {
    return newInstance(expenseDaoProvider.get(), categoryDaoProvider.get());
  }

  public static StatisticsViewModel_Factory create(Provider<ExpenseDao> expenseDaoProvider,
      Provider<CategoryDao> categoryDaoProvider) {
    return new StatisticsViewModel_Factory(expenseDaoProvider, categoryDaoProvider);
  }

  public static StatisticsViewModel newInstance(ExpenseDao expenseDao, CategoryDao categoryDao) {
    return new StatisticsViewModel(expenseDao, categoryDao);
  }
}
