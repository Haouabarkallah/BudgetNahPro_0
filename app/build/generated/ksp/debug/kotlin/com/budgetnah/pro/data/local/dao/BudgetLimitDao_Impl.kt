package com.budgetnah.pro.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.budgetnah.pro.`data`.local.entities.BudgetLimit
import javax.`annotation`.processing.Generated
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
public class BudgetLimitDao_Impl(
  __db: RoomDatabase,
) : BudgetLimitDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfBudgetLimit: EntityInsertAdapter<BudgetLimit>

  private val __deleteAdapterOfBudgetLimit: EntityDeleteOrUpdateAdapter<BudgetLimit>
  init {
    this.__db = __db
    this.__insertAdapterOfBudgetLimit = object : EntityInsertAdapter<BudgetLimit>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `budget_limits` (`id`,`categoryId`,`amount`,`userId`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BudgetLimit) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.categoryId.toLong())
        statement.bindDouble(3, entity.amount)
        statement.bindLong(4, entity.userId.toLong())
      }
    }
    this.__deleteAdapterOfBudgetLimit = object : EntityDeleteOrUpdateAdapter<BudgetLimit>() {
      protected override fun createQuery(): String = "DELETE FROM `budget_limits` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: BudgetLimit) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
  }

  public override suspend fun upsertBudgetLimit(budgetLimit: BudgetLimit): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfBudgetLimit.insertAndReturnId(_connection, budgetLimit)
    _result
  }

  public override suspend fun deleteBudgetLimit(budgetLimit: BudgetLimit): Unit =
      performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfBudgetLimit.handle(_connection, budgetLimit)
  }

  public override fun getBudgetLimitsForUser(userId: Int): Flow<List<BudgetLimit>> {
    val _sql: String = "SELECT * FROM budget_limits WHERE userId = ?"
    return createFlow(__db, false, arrayOf("budget_limits")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _cursorIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _cursorIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _result: MutableList<BudgetLimit> = mutableListOf()
        while (_stmt.step()) {
          val _item: BudgetLimit
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_cursorIndexOfCategoryId).toInt()
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_cursorIndexOfAmount)
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_cursorIndexOfUserId).toInt()
          _item = BudgetLimit(_tmpId,_tmpCategoryId,_tmpAmount,_tmpUserId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
