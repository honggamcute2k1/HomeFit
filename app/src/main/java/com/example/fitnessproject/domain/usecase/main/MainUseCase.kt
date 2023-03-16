package com.example.fitnessproject.domain.usecase.main

import com.example.fitnessproject.data.local.entity.Topic

interface MainUseCase {
    suspend fun getAllTopic(): List<Topic>
}