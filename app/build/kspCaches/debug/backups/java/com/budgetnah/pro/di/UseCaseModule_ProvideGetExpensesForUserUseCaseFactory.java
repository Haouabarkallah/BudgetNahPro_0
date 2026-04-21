package com.budgetnah.pro.di;

import com.budgetnah.pro.domain.usecase.dashboard.GetExpensesForUserUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class UseCaseModule_ProvideGetExpensesForUserUseCaseFactory implements Factory<GetExpensesForUserUseCase> {
  @Override
  public GetExpensesForUserUseCase get() {
    return provideGetExpensesForUserUseCase();
  }

  public static UseCaseModule_ProvideGetExpensesForUserUseCaseFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetExpensesForUserUseCase provideGetExpensesForUserUseCase() {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetExpensesForUserUseCase());
  }

  private static final class InstanceHolder {
    static final UseCaseModule_ProvideGetExpensesForUserUseCaseFactory INSTANCE = new UseCaseModule_ProvideGetExpensesForUserUseCaseFactory();
  }
}
