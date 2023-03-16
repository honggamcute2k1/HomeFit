package com.example.fitnessproject.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic_detail_selected")
data class TopicDetailSelected(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "topic_detail_id")
    val topicDetailId: Int,

    @ColumnInfo(name = "topic_selected_id")
    val topicSelectedId: Int,

    @ColumnInfo(name = "id_topic")
    val idTopic: Int,

    @ColumnInfo(name = "state")
    val state: Int

)