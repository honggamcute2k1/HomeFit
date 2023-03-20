package com.example.fitnessproject.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitnessproject.data.local.entity.TopicDetail

@Dao
interface TopicDetailDao {
    @Query("SELECT * FROM detail_topic WHERE id_topic = :idTopic")
    fun getAllTopicDetail(idTopic: Int): List<TopicDetail>

    @Query("SELECT * FROM detail_topic WHERE id_detail = :idDetail")
    fun getTopicDetail(idDetail: Int): TopicDetail
}