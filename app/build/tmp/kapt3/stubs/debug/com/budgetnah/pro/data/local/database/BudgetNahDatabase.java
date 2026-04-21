package com.budgetnah.pro.data.local.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lcom/budgetnah/pro/data/local/database/BudgetNahDatabase;", "Landroidx/room/RoomDatabase;", "()V", "budgetLimitDao", "Lcom/budgetnah/pro/data/local/dao/BudgetLimitDao;", "categoryDao", "Lcom/budgetnah/pro/data/local/dao/CategoryDao;", "expenseDao", "Lcom/budgetnah/pro/data/local/dao/ExpenseDao;", "userDao", "Lcom/budgetnah/pro/data/local/dao/UserDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.budgetnah.pro.data.local.entities.User.class, com.budgetnah.pro.data.local.entities.Category.class, com.budgetnah.pro.data.local.entities.Expense.class, com.budgetnah.pro.data.local.entities.BudgetLimit.class}, version = 1, exportSchema = false)
public abstract class BudgetNahDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.budgetnah.pro.data.local.database.BudgetNahDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.budgetnah.pro.data.local.database.BudgetNahDatabase.Companion Companion = null;
    
    public BudgetNahDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgetnah.pro.data.local.dao.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgetnah.pro.data.local.dao.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgetnah.pro.data.local.dao.ExpenseDao expenseDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgetnah.pro.data.local.dao.BudgetLimitDao budgetLimitDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/budgetnah/pro/data/local/database/BudgetNahDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/budgetnah/pro/data/local/database/BudgetNahDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.budgetnah.pro.data.local.database.BudgetNahDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}