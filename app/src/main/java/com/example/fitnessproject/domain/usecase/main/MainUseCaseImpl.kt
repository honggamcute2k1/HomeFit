package com.example.fitnessproject.domain.usecase.main

import com.example.fitnessproject.data.local.repository.TopicRepository
import com.example.fitnessproject.domain.model.TopicDetailModel
import com.example.fitnessproject.domain.model.TopicModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainUseCaseImpl(private val topicRepository: TopicRepository) : MainUseCase {
    override suspend fun getAllTopic(): List<TopicModel> {
        return topicRepository.getAllTopic().map {
            TopicModel.toTopicModel(it)
        }
    }

    override suspend fun getAllTopicById(idTopic: Int): List<TopicDetailModel> {
        return topicRepository.getAllTopicDetail(idTopic).map {
            TopicDetailModel.toTopicDetailModel(it)
        }
    }

    override suspend fun getTopicDetailById(idDetail: Int): TopicDetailModel {
        return withContext(Dispatchers.IO) {
            val topicDetail = topicRepository.getTopicDetailById(idDetail)
            TopicDetailModel.toTopicDetailModel(topicDetail)
        }
    }
}