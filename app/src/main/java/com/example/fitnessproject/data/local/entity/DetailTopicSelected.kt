package com.example.fitnessproject.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_topic_selected")
data class DetailTopicSelected(
    @PrimaryKey
    @ColumnInfo(name = "id_detail")
    val idDetail: Int,

    @PrimaryKey
    @ColumnInfo(name = "id_topic")
    val idTopic: Int,

    @PrimaryKey
    @ColumnInfo(name = "id_user")
    val idUser: Int,

    @ColumnInfo(name = "state")
    val state: String
)