package com.budgetnah.pro.di;

import com.budgetnah.pro.domain.repository.AuthRepository;
import com.budgetnah.pro.domain.usecase.LoginUseCase;
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
public final class UseCaseModule_ProvideLoginUseCaseFactory implements Factory<LoginUseCase> {
  private final Provider<AuthRepository> repositoryProvider;

  private UseCaseModule_ProvideLoginUseCaseFactory(Provider<AuthRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LoginUseCase get() {
    return provideLoginUseCase(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideLoginUseCaseFactory create(
      Provider<AuthRepository> repositoryProvider) {
    return new UseCaseModule_ProvideLoginUseCaseFactory(repositoryProvider);
  }

  public static LoginUseCase provideLoginUseCase(AuthRepository repository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideLoginUseCase(repository));
  }
}
