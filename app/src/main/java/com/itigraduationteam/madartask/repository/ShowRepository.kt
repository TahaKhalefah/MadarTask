package com.itigraduationteam.madartask.repository

import com.itigraduationteam.madartask.db.UserDao
import javax.inject.Inject

class ShowRepository
@Inject
constructor(
    val userDao: UserDao
) {
    fun getAllUsers() = userDao.getAllUsers()
}