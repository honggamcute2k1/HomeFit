package com.example.fitnessproject.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitnessproject.data.local.entity.Topic
import com.example.fitnessproject.data.local.entity.TopicDetail

@Dao
interface TopicDao {
    @Query("SELECT * FROM topic")
    fun getAllTopic(): List<Topic>
}