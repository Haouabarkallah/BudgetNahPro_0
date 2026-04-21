package com.budgetnah.pro.di;

import com.budgetnah.pro.domain.usecase.dashboard.GetBudgetLimitsForUserUseCase;
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
    "cast"
})
public final class UseCaseModule_ProvideGetBudgetLimitsForUserUseCaseFactory implements Factory<GetBudgetLimitsForUserUseCase> {
  @Override
  public GetBudgetLimitsForUserUseCase get() {
    return provideGetBudgetLimitsForUserUseCase();
  }

  public static UseCaseModule_ProvideGetBudgetLimitsForUserUseCaseFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetBudgetLimitsForUserUseCase provideGetBudgetLimitsForUserUseCase() {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetBudgetLimitsForUserUseCase());
  }

  private static final class InstanceHolder {
    private static final UseCaseModule_ProvideGetBudgetLimitsForUserUseCaseFactory INSTANCE = new UseCaseModule_ProvideGetBudgetLimitsForUserUseCaseFactory();
  }
}
