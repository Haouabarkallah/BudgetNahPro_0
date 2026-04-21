package com.budgetnah.pro.di;

import com.budgetnah.pro.domain.usecase.dashboard.GetLoggedInUserUseCase;
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
public final class UseCaseModule_ProvideGetLoggedInUserUseCaseFactory implements Factory<GetLoggedInUserUseCase> {
  @Override
  public GetLoggedInUserUseCase get() {
    return provideGetLoggedInUserUseCase();
  }

  public static UseCaseModule_ProvideGetLoggedInUserUseCaseFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetLoggedInUserUseCase provideGetLoggedInUserUseCase() {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetLoggedInUserUseCase());
  }

  private static final class InstanceHolder {
    private static final UseCaseModule_ProvideGetLoggedInUserUseCaseFactory INSTANCE = new UseCaseModule_ProvideGetLoggedInUserUseCaseFactory();
  }
}
