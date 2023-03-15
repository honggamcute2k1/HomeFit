package com.example.fitnessproject.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic_selected")
data class TopicSelected(
    @PrimaryKey
    @ColumnInfo(name = "id_topic")
    val idTopic: Int,

    @PrimaryKey
    @ColumnInfo(name = "id_user")
    val idUser: Int
)