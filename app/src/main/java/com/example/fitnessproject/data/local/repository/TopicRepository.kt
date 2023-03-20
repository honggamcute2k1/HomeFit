package com.example.fitnessproject.data.local.repository

import com.example.fitnessproject.data.local.entity.Topic
import com.example.fitnessproject.data.local.entity.TopicDetail

interface TopicRepository {
    suspend fun getAllTopic(): List<Topic>
    suspend fun getAllTopicDetail(idTopic: Int): List<TopicDetail>
    suspend fun getTopicDetailById(idDetail: Int): TopicDetail
}