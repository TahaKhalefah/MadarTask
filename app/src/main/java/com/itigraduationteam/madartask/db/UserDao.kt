package com.itigraduationteam.madartask.db

import android.database.sqlite.SQLiteDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.itigraduationteam.madartask.model.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = SQLiteDatabase.CONFLICT_REPLACE)
    fun insertUser(user: User): Completable

    @Query("select * from User")
    fun getAllUsers(): Single<List<User>>
}