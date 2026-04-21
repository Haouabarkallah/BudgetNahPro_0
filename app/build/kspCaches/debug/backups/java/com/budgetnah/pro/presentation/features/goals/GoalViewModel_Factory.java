package com.budgetnah.pro.presentation.features.goals;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class GoalViewModel_Factory implements Factory<GoalViewModel> {
  @Override
  public GoalViewModel get() {
    return newInstance();
  }

  public static GoalViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GoalViewModel newInstance() {
    return new GoalViewModel();
  }

  private static final class InstanceHolder {
    static final GoalViewModel_Factory INSTANCE = new GoalViewModel_Factory();
  }
}
