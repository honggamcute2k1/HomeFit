package com.example.fitnessproject.ui.activities.videodetail

import android.app.Application
import com.example.fitnessproject.ui.activities.topicdetail.TopicDetailViewModel
import kotlinx.coroutines.launch

class VideoDetailViewModel(application: Application) : TopicDetailViewModel(application) {

    fun getListTopicDetail(idTopic: Int) {
        showLoading(isShowLoading = true)
        myScope.launch {
            val list = mainUseCase.getAllTopicById(idTopic = idTopic)
            topicDetailLiveData.value = list
        }
    }
}