package com.itigraduationteam.madartask.repository

import com.itigraduationteam.madartask.db.UserDao
import com.itigraduationteam.madartask.model.User
import javax.inject.Inject

class SaveRepository
@Inject
constructor(
    val userDao: UserDao
) {
    fun insertUser(user: User) = userDao.insertUser(user)
}