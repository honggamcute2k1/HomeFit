package com.example.fitnessproject.ui.activities.topicdetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.domain.model.TopicDetailModel
import com.example.fitnessproject.domain.model.TopicModel
import com.example.fitnessproject.domain.usecase.main.MainUseCase
import com.example.fitnessproject.domain.usecase.main.MainUseCaseImpl
import com.example.fitnessproject.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopicDetailViewModel(application: Application) : BaseViewModel(application) {
    private val mainUseCase: MainUseCase =
        MainUseCaseImpl((application as FitnessApplication).topicRepository)

    val topicDetailLiveData = MutableLiveData<List<TopicDetailModel>>()
    val detailLiveData = MutableLiveData<TopicDetailModel>()


    fun getListTopicDetailOfTopic(topicModel: TopicModel) {
        showLoading(isShowLoading = true)
        myScope.launch {
            delay(2000)
            val topicListDetail = withContext(Dispatchers.IO) {
                mainUseCase.getAllTopicById(idTopic = topicModel.idTopic)
            }
            topicDetailLiveData.value = topicListDetail
            showLoading(isShowLoading = false)
        }
    }

    fun getTopicDetail(topicDetailModel: TopicDetailModel) {
        showLoading(isShowLoading = true)
        myScope.launch {
            val model = mainUseCase.getTopicDetailById(topicDetailModel.idDetail)
            detailLiveData.value = model
            showLoading(isShowLoading = false)
        }
    }
}