package com.example.fitnessproject.domain.usecase.user

import com.example.fitnessproject.data.local.entity.User
import com.example.fitnessproject.data.local.repository.UserRepository

class UserUseCaseImpl(private val userRepository: UserRepository) : UserUseCase {
    override suspend fun insertUser(user: User) {
        return userRepository.insertUser(user)
    }

    override suspend fun getAllUser(): List<User> {
        return userRepository.getAllUser()
    }
}