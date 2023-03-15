package com.example.fitnessproject.domain.usecase.user

import com.example.fitnessproject.data.local.entity.User

interface UserUseCase {
    suspend fun insertUser(user: User)

    suspend fun getAllUser(): List<User>

}