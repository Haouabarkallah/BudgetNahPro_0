package com.budgetnah.pro.presentation.features.expenses;

import com.budgetnah.pro.domain.repository.ExpenseRepository;
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
public final class ExpenseViewModel_Factory implements Factory<ExpenseViewModel> {
  private final Provider<ExpenseRepository> repositoryProvider;

  private ExpenseViewModel_Factory(Provider<ExpenseRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ExpenseViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ExpenseViewModel_Factory create(Provider<ExpenseRepository> repositoryProvider) {
    return new ExpenseViewModel_Factory(repositoryProvider);
  }

  public static ExpenseViewModel newInstance(ExpenseRepository repository) {
    return new ExpenseViewModel(repository);
  }
}
