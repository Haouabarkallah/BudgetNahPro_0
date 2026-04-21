package com.budgetnah.pro.data.di;

import com.budgetnah.pro.data.local.dao.BudgetLimitDao;
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
public final class DatabaseModule_ProvideBudgetLimitDaoFactory implements Factory<BudgetLimitDao> {
  private final Provider<BudgetNahDatabase> databaseProvider;

  private DatabaseModule_ProvideBudgetLimitDaoFactory(
      Provider<BudgetNahDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public BudgetLimitDao get() {
    return provideBudgetLimitDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideBudgetLimitDaoFactory create(
      Provider<BudgetNahDatabase> databaseProvider) {
    return new DatabaseModule_ProvideBudgetLimitDaoFactory(databaseProvider);
  }

  public static BudgetLimitDao provideBudgetLimitDao(BudgetNahDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideBudgetLimitDao(database));
  }
}
