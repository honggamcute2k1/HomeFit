package com.example.fitnessproject.domain.usecase.main

import com.example.fitnessproject.data.local.entity.Topic
import com.example.fitnessproject.data.local.repository.TopicRepository

class MainUseCaseImpl(private val topicRepository: TopicRepository) : MainUseCase {
    override suspend fun getAllTopic(): List<Topic> {
        return topicRepository.getAllTopic().map {
            it
        }
    }
}