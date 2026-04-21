package com.budgetnah.pro.data.local.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u00112\u0006\u0010\n\u001a\u00020\u000bH\'J(\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0016"}, d2 = {"Lcom/budgetnah/pro/data/local/database/dao/ExpenseDao;", "", "deleteExpense", "", "expense", "Lcom/budgetnah/pro/data/local/entities/Expense;", "(Lcom/budgetnah/pro/data/local/entities/Expense;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExpensesByCategory", "", "Lcom/budgetnah/pro/data/local/database/dao/CategoryExpenseSummary;", "userId", "", "start", "Ljava/util/Date;", "end", "(ILjava/util/Date;Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExpensesByUser", "Lkotlinx/coroutines/flow/Flow;", "getTotalExpensesBetween", "", "insertExpense", "", "app_debug"})
@androidx.room.Dao()
public abstract interface ExpenseDao {
    
    @androidx.room.Query(value = "SELECT * FROM expenses WHERE userId = :userId ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.budgetnah.pro.data.local.entities.Expense>> getExpensesByUser(int userId);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertExpense(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.data.local.entities.Expense expense, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteExpense(@org.jetbrains.annotations.NotNull()
    com.budgetnah.pro.data.local.entities.Expense expense, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM expenses WHERE userId = :userId AND date BETWEEN :start AND :end")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalExpensesBetween(int userId, @org.jetbrains.annotations.NotNull()
    java.util.Date start, @org.jetbrains.annotations.NotNull()
    java.util.Date end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT categoryId, SUM(amount) as total FROM expenses WHERE userId = :userId AND date BETWEEN :start AND :end GROUP BY categoryId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExpensesByCategory(int userId, @org.jetbrains.annotations.NotNull()
    java.util.Date start, @org.jetbrains.annotations.NotNull()
    java.util.Date end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.budgetnah.pro.data.local.database.dao.CategoryExpenseSummary>> $completion);
}