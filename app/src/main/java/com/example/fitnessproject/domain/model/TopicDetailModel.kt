package com.example.fitnessproject.domain.model

import android.os.Parcelable
import com.example.fitnessproject.data.local.entity.TopicDetail
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopicDetailModel(
    val idDetail: Int,
    val name: String,
    val description: String,
    val url: String,
    val idTopic: Int
) : Parcelable {
    companion object {
        fun toTopicDetailModel(src: TopicDetail): TopicDetailModel {
            return TopicDetailModel(
                src.idTopic,
                src.name,
                src.description,
                src.url,
                src.idTopic
            )
        }
    }
}