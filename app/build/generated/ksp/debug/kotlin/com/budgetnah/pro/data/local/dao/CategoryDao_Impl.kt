package com.budgetnah.pro.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.budgetnah.pro.`data`.local.entities.Category
import javax.`annotation`.processing.Generated
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
public class CategoryDao_Impl(
  __db: RoomDatabase,
) : CategoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCategory: EntityInsertAdapter<Category>

  private val __deleteAdapterOfCategory: EntityDeleteOrUpdateAdapter<Category>
  init {
    this.__db = __db
    this.__insertAdapterOfCategory = object : EntityInsertAdapter<Category>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `categories` (`id`,`name`,`iconRes`,`color`,`userId`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Category) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindLong(3, entity.iconRes.toLong())
        statement.bindLong(4, entity.color)
        statement.bindLong(5, entity.userId.toLong())
      }
    }
    this.__deleteAdapterOfCategory = object : EntityDeleteOrUpdateAdapter<Category>() {
      protected override fun createQuery(): String = "DELETE FROM `categories` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Category) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertCategory(category: Category): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfCategory.insertAndReturnId(_connection, category)
    _result
  }

  public override suspend fun deleteCategory(category: Category): Unit = performSuspending(__db,
      false, true) { _connection ->
    __deleteAdapterOfCategory.handle(_connection, category)
  }

  public override fun getUserCategories(userId: Int): Flow<List<Category>> {
    val _sql: String =
        "SELECT * FROM categories WHERE id IN (SELECT categoryId FROM budget_limits WHERE userId = ?) OR id IN (SELECT DISTINCT categoryId FROM expenses WHERE userId = ?)"
    return createFlow(__db, false, arrayOf("categories", "budget_limits", "expenses")) {
        _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, userId.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfIconRes: Int = getColumnIndexOrThrow(_stmt, "iconRes")
        val _cursorIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _cursorIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _result: MutableList<Category> = mutableListOf()
        while (_stmt.step()) {
          val _item: Category
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpIconRes: Int
          _tmpIconRes = _stmt.getLong(_cursorIndexOfIconRes).toInt()
          val _tmpColor: Long
          _tmpColor = _stmt.getLong(_cursorIndexOfColor)
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_cursorIndexOfUserId).toInt()
          _item = Category(_tmpId,_tmpName,_tmpIconRes,_tmpColor,_tmpUserId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllCategories(userId: Int): Flow<List<Category>> {
    val _sql: String = "SELECT * FROM categories WHERE userId = ?"
    return createFlow(__db, false, arrayOf("categories")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfIconRes: Int = getColumnIndexOrThrow(_stmt, "iconRes")
        val _cursorIndexOfColor: Int = getColumnIndexOrThrow(_stmt, "color")
        val _cursorIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _result: MutableList<Category> = mutableListOf()
        while (_stmt.step()) {
          val _item: Category
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpIconRes: Int
          _tmpIconRes = _stmt.getLong(_cursorIndexOfIconRes).toInt()
          val _tmpColor: Long
          _tmpColor = _stmt.getLong(_cursorIndexOfColor)
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_cursorIndexOfUserId).toInt()
          _item = Category(_tmpId,_tmpName,_tmpIconRes,_tmpColor,_tmpUserId)
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
