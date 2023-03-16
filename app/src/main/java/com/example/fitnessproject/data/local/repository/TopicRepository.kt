package com.example.fitnessproject.data.local.repository

import com.example.fitnessproject.data.local.entity.Topic

interface TopicRepository {
    suspend fun getAllTopic(): List<Topic>
}