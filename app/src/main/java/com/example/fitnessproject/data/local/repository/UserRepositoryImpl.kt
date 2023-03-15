package com.example.fitnessproject.data.local.repository

import androidx.lifecycle.LiveData
import com.example.fitnessproject.data.local.dao.UserDao
import com.example.fitnessproject.data.local.entity.User

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getAllUser(): List<User> {
        return userDao.getAllUser()
    }

    suspend fun getAllUserTest(): LiveData<List<User>> {
        return userDao.getAllUserTest()
    }
}