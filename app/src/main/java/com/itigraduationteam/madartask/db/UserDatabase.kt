package com.itigraduationteam.madartask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itigraduationteam.madartask.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}