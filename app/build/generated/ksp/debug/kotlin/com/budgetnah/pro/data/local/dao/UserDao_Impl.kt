package com.budgetnah.pro.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.budgetnah.pro.`data`.local.entities.User
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUser: EntityInsertAdapter<User>

  private val __updateAdapterOfUser: EntityDeleteOrUpdateAdapter<User>
  init {
    this.__db = __db
    this.__insertAdapterOfUser = object : EntityInsertAdapter<User>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `users` (`id`,`email`,`passwordHash`,`fullName`,`isLoggedIn`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.email)
        statement.bindText(3, entity.passwordHash)
        statement.bindText(4, entity.fullName)
        val _tmp: Int = if (entity.isLoggedIn) 1 else 0
        statement.bindLong(5, _tmp.toLong())
      }
    }
    this.__updateAdapterOfUser = object : EntityDeleteOrUpdateAdapter<User>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `users` SET `id` = ?,`email` = ?,`passwordHash` = ?,`fullName` = ?,`isLoggedIn` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.email)
        statement.bindText(3, entity.passwordHash)
        statement.bindText(4, entity.fullName)
        val _tmp: Int = if (entity.isLoggedIn) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        statement.bindLong(6, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertUser(user: User): Long = performSuspending(__db, false, true) {
      _connection ->
    val _result: Long = __insertAdapterOfUser.insertAndReturnId(_connection, user)
    _result
  }

  public override suspend fun updateUser(user: User): Unit = performSuspending(__db, false, true) {
      _connection ->
    __updateAdapterOfUser.handle(_connection, user)
  }

  public override suspend fun getUserByEmail(email: String): User? {
    val _sql: String = "SELECT * FROM users WHERE email = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _cursorIndexOfPasswordHash: Int = getColumnIndexOrThrow(_stmt, "passwordHash")
        val _cursorIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _cursorIndexOfIsLoggedIn: Int = getColumnIndexOrThrow(_stmt, "isLoggedIn")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_cursorIndexOfEmail)
          val _tmpPasswordHash: String
          _tmpPasswordHash = _stmt.getText(_cursorIndexOfPasswordHash)
          val _tmpFullName: String
          _tmpFullName = _stmt.getText(_cursorIndexOfFullName)
          val _tmpIsLoggedIn: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsLoggedIn).toInt()
          _tmpIsLoggedIn = _tmp != 0
          _result = User(_tmpId,_tmpEmail,_tmpPasswordHash,_tmpFullName,_tmpIsLoggedIn)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getLoggedInUser(): Flow<User?> {
    val _sql: String = "SELECT * FROM users WHERE isLoggedIn = 1 LIMIT 1"
    return createFlow(__db, false, arrayOf("users")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _cursorIndexOfPasswordHash: Int = getColumnIndexOrThrow(_stmt, "passwordHash")
        val _cursorIndexOfFullName: Int = getColumnIndexOrThrow(_stmt, "fullName")
        val _cursorIndexOfIsLoggedIn: Int = getColumnIndexOrThrow(_stmt, "isLoggedIn")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_cursorIndexOfEmail)
          val _tmpPasswordHash: String
          _tmpPasswordHash = _stmt.getText(_cursorIndexOfPasswordHash)
          val _tmpFullName: String
          _tmpFullName = _stmt.getText(_cursorIndexOfFullName)
          val _tmpIsLoggedIn: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsLoggedIn).toInt()
          _tmpIsLoggedIn = _tmp != 0
          _result = User(_tmpId,_tmpEmail,_tmpPasswordHash,_tmpFullName,_tmpIsLoggedIn)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun logoutAllUsers() {
    val _sql: String = "UPDATE users SET isLoggedIn = 0"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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
