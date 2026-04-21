package com.budgetnah.pro.data.di;

import com.budgetnah.pro.data.local.dao.CategoryDao;
import com.budgetnah.pro.domain.repository.CategoryRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RepositoryModule_Companion_ProvideCategoryRepositoryFactory implements Factory<CategoryRepository> {
  private final Provider<CategoryDao> daoProvider;

  private RepositoryModule_Companion_ProvideCategoryRepositoryFactory(
      Provider<CategoryDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public CategoryRepository get() {
    return provideCategoryRepository(daoProvider.get());
  }

  public static RepositoryModule_Companion_ProvideCategoryRepositoryFactory create(
      Provider<CategoryDao> daoProvider) {
    return new RepositoryModule_Companion_ProvideCategoryRepositoryFactory(daoProvider);
  }

  public static CategoryRepository provideCategoryRepository(CategoryDao dao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.Companion.provideCategoryRepository(dao));
  }
}
