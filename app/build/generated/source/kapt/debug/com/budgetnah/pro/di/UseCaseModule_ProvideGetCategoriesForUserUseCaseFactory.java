package com.budgetnah.pro.di;

import com.budgetnah.pro.domain.usecase.dashboard.GetCategoriesForUserUseCase;
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
public final class UseCaseModule_ProvideGetCategoriesForUserUseCaseFactory implements Factory<GetCategoriesForUserUseCase> {
  @Override
  public GetCategoriesForUserUseCase get() {
    return provideGetCategoriesForUserUseCase();
  }

  public static UseCaseModule_ProvideGetCategoriesForUserUseCaseFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetCategoriesForUserUseCase provideGetCategoriesForUserUseCase() {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetCategoriesForUserUseCase());
  }

  private static final class InstanceHolder {
    private static final UseCaseModule_ProvideGetCategoriesForUserUseCaseFactory INSTANCE = new UseCaseModule_ProvideGetCategoriesForUserUseCaseFactory();
  }
}
