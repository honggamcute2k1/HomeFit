package com.example.fitnessproject.domain.usecase.main

import com.example.fitnessproject.domain.model.TopicDetailModel
import com.example.fitnessproject.domain.model.TopicModel

interface MainUseCase {
    suspend fun getAllTopic(): List<TopicModel>
    suspend fun getAllTopicById(idTopic: Int): List<TopicDetailModel>
    suspend fun getTopicDetailById(idDetail: Int): TopicDetailModel
}