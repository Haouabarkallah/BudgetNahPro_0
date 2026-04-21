package com.budgetnah.pro.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.budgetnah.pro.`data`.local.entities.Expense
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ExpenseDao_Impl(
  __db: RoomDatabase,
) : ExpenseDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfExpense: EntityInsertAdapter<Expense>

  private val __deleteAdapterOfExpense: EntityDeleteOrUpdateAdapter<Expense>
  init {
    this.__db = __db
    this.__insertAdapterOfExpense = object : EntityInsertAdapter<Expense>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `expenses` (`id`,`amount`,`categoryId`,`date`,`userId`,`synced`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Expense) {
        statement.bindText(1, entity.id)
        statement.bindDouble(2, entity.amount)
        statement.bindLong(3, entity.categoryId.toLong())
        statement.bindLong(4, entity.date)
        statement.bindLong(5, entity.userId.toLong())
        val _tmp: Int = if (entity.synced) 1 else 0
        statement.bindLong(6, _tmp.toLong())
      }
    }
    this.__deleteAdapterOfExpense = object : EntityDeleteOrUpdateAdapter<Expense>() {
      protected override fun createQuery(): String = "DELETE FROM `expenses` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Expense) {
        statement.bindText(1, entity.id)
      }
    }
  }

  public override suspend fun insertExpense(expense: Expense): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfExpense.insertAndReturnId(_connection, expense)
    _result
  }

  public override suspend fun deleteExpense(expense: Expense): Unit = performSuspending(__db, false,
      true) { _connection ->
    __deleteAdapterOfExpense.handle(_connection, expense)
  }

  public override fun getExpensesByUser(userId: Int): Flow<List<Expense>> {
    val _sql: String = "SELECT * FROM expenses WHERE userId = ? ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("expenses")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _cursorIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _cursorIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _cursorIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _cursorIndexOfSynced: Int = getColumnIndexOrThrow(_stmt, "synced")
        val _result: MutableList<Expense> = mutableListOf()
        while (_stmt.step()) {
          val _item: Expense
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_cursorIndexOfAmount)
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_cursorIndexOfCategoryId).toInt()
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_cursorIndexOfDate)
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_cursorIndexOfUserId).toInt()
          val _tmpSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfSynced).toInt()
          _tmpSynced = _tmp != 0
          _item = Expense(_tmpId,_tmpAmount,_tmpCategoryId,_tmpDate,_tmpUserId,_tmpSynced)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUnsyncedExpenses(): List<Expense> {
    val _sql: String = "SELECT * FROM expenses WHERE synced = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _cursorIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _cursorIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _cursorIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _cursorIndexOfSynced: Int = getColumnIndexOrThrow(_stmt, "synced")
        val _result: MutableList<Expense> = mutableListOf()
        while (_stmt.step()) {
          val _item: Expense
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_cursorIndexOfAmount)
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_cursorIndexOfCategoryId).toInt()
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_cursorIndexOfDate)
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_cursorIndexOfUserId).toInt()
          val _tmpSynced: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfSynced).toInt()
          _tmpSynced = _tmp != 0
          _item = Expense(_tmpId,_tmpAmount,_tmpCategoryId,_tmpDate,_tmpUserId,_tmpSynced)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTotalExpensesBetween(
    userId: Int,
    start: Long,
    end: Long,
  ): Double? {
    val _sql: String = """
        |
        |        SELECT SUM(amount) FROM expenses 
        |        WHERE userId = ? 
        |        AND date BETWEEN ? AND ?
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, start)
        _argIndex = 3
        _stmt.bindLong(_argIndex, end)
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getExpensesByCategory(
    userId: Int,
    start: Long,
    end: Long,
  ): List<CategoryExpenseSummary> {
    val _sql: String = """
        |
        |        SELECT e.categoryId, c.name, SUM(e.amount) as total, c.color 
        |        FROM expenses e
        |        JOIN categories c ON e.categoryId = c.id
        |        WHERE e.userId = ? 
        |        AND e.date BETWEEN ? AND ? 
        |        GROUP BY e.categoryId
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, start)
        _argIndex = 3
        _stmt.bindLong(_argIndex, end)
        val _cursorIndexOfCategoryId: Int = 0
        val _cursorIndexOfName: Int = 1
        val _cursorIndexOfTotal: Int = 2
        val _cursorIndexOfColor: Int = 3
        val _result: MutableList<CategoryExpenseSummary> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoryExpenseSummary
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_cursorIndexOfCategoryId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpTotal: Double
          _tmpTotal = _stmt.getDouble(_cursorIndexOfTotal)
          val _tmpColor: Int
          _tmpColor = _stmt.getLong(_cursorIndexOfColor).toInt()
          _item = CategoryExpenseSummary(_tmpCategoryId,_tmpName,_tmpTotal,_tmpColor)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String) {
    val _sql: String = "UPDATE expenses SET synced = 1 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
