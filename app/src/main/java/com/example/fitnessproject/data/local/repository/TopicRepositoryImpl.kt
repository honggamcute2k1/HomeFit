package com.example.fitnessproject.data.local.repository

import com.example.fitnessproject.data.local.dao.TopicDao
import com.example.fitnessproject.data.local.entity.Topic

class TopicRepositoryImpl(private val topicDao: TopicDao) : TopicRepository {
    override suspend fun getAllTopic(): List<Topic> {
        return topicDao.getAllTopic()
    }
}