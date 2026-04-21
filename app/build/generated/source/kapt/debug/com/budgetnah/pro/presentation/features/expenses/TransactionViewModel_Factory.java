package com.budgetnah.pro.presentation.features.expenses;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
    "cast"
})
public final class TransactionViewModel_Factory implements Factory<ExpenseViewModel> {
  @Override
  public ExpenseViewModel get() {
    return newInstance();
  }

  public static TransactionViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ExpenseViewModel newInstance() {
    return new ExpenseViewModel();
  }

  private static final class InstanceHolder {
    private static final TransactionViewModel_Factory INSTANCE = new TransactionViewModel_Factory();
  }
}
