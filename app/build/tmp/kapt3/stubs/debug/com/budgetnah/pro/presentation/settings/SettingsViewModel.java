package com.budgetnah.pro.presentation.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0006J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/budgetnah/pro/presentation/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/budgetnah/pro/domain/repository/AuthRepository;", "(Lcom/budgetnah/pro/domain/repository/AuthRepository;)V", "exportTransactions", "", "context", "Landroid/content/Context;", "logout", "shareFile", "file", "Ljava/io/File;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetnah.pro.domain.repository.AuthRepository authRepository = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.domain.repository.AuthRepository authRepository) {
        super();
    }
    
    public final void exportTransactions(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final void shareFile(android.content.Context context, java.io.File file) {
    }
    
    public final void logout() {
    }
}