package com.example.fitnessproject.data.local.repository

import com.example.fitnessproject.data.local.entity.User

interface UserRepository {
    suspend fun insertUser(user: User)

    suspend fun getAllUser(): List<User>

}