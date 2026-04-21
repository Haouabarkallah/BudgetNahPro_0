package com.budgetnah.pro.data.di;

import com.budgetnah.pro.data.local.dao.ExpenseDao;
import com.budgetnah.pro.data.local.database.BudgetNahDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideExpenseDaoFactory implements Factory<ExpenseDao> {
  private final Provider<BudgetNahDatabase> databaseProvider;

  private DatabaseModule_ProvideExpenseDaoFactory(Provider<BudgetNahDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ExpenseDao get() {
    return provideExpenseDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideExpenseDaoFactory create(
      Provider<BudgetNahDatabase> databaseProvider) {
    return new DatabaseModule_ProvideExpenseDaoFactory(databaseProvider);
  }

  public static ExpenseDao provideExpenseDao(BudgetNahDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExpenseDao(database));
  }
}
