package com.example.fitnessproject.data.local.repository

import com.example.fitnessproject.data.local.dao.TopicDao
import com.example.fitnessproject.data.local.dao.TopicDetailDao
import com.example.fitnessproject.data.local.entity.Topic
import com.example.fitnessproject.data.local.entity.TopicDetail

class TopicRepositoryImpl(
    private val topicDao: TopicDao,
    private val topicDetailDao: TopicDetailDao
) : TopicRepository {
    override suspend fun getAllTopic(): List<Topic> {
        return topicDao.getAllTopic()
    }

    override suspend fun getAllTopicDetail(idTopic: Int): List<TopicDetail> {
        return topicDetailDao.getAllTopicDetail(idTopic)
    }

    override suspend fun getTopicDetailById(idDetail: Int): TopicDetail {
        return topicDetailDao.getTopicDetail(idDetail)
    }
}