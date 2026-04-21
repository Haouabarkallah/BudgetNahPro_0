package com.budgetnah.pro.di;

import com.budgetnah.pro.domain.repository.AuthRepository;
import com.budgetnah.pro.domain.usecase.RegisterUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class UseCaseModule_ProvideRegisterUseCaseFactory implements Factory<RegisterUseCase> {
  private final Provider<AuthRepository> repositoryProvider;

  public UseCaseModule_ProvideRegisterUseCaseFactory(Provider<AuthRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RegisterUseCase get() {
    return provideRegisterUseCase(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideRegisterUseCaseFactory create(
      Provider<AuthRepository> repositoryProvider) {
    return new UseCaseModule_ProvideRegisterUseCaseFactory(repositoryProvider);
  }

  public static RegisterUseCase provideRegisterUseCase(AuthRepository repository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideRegisterUseCase(repository));
  }
}
