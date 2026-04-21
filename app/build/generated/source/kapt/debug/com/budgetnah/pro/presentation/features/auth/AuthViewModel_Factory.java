package com.budgetnah.pro.presentation.features.auth;

import com.budgetnah.pro.domain.repository.AuthRepository;
import com.budgetnah.pro.domain.usecase.LoginUseCase;
import com.budgetnah.pro.domain.usecase.RegisterUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<LoginUseCase> loginUseCaseProvider;

  private final Provider<RegisterUseCase> registerUseCaseProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  public AuthViewModel_Factory(Provider<LoginUseCase> loginUseCaseProvider,
      Provider<RegisterUseCase> registerUseCaseProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.loginUseCaseProvider = loginUseCaseProvider;
    this.registerUseCaseProvider = registerUseCaseProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(loginUseCaseProvider.get(), registerUseCaseProvider.get(), authRepositoryProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<LoginUseCase> loginUseCaseProvider,
      Provider<RegisterUseCase> registerUseCaseProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new AuthViewModel_Factory(loginUseCaseProvider, registerUseCaseProvider, authRepositoryProvider);
  }

  public static AuthViewModel newInstance(LoginUseCase loginUseCase,
      RegisterUseCase registerUseCase, AuthRepository authRepository) {
    return new AuthViewModel(loginUseCase, registerUseCase, authRepository);
  }
}
