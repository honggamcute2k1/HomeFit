package com.example.fitnessproject.ui.activities.topicdetail

import android.util.Log
import com.example.fitnessproject.R
import com.example.fitnessproject.domain.model.TopicDetailModel
import com.example.fitnessproject.ui.BaseActivity

class TopicDetailActivity : BaseActivity<TopicDetailViewModel>() {
    override fun getLayoutId() = R.layout.activity_topic_detail

    companion object {
        const val KEY_TOPIC_DETAIL = "KEY_TOPIC_DETAIL"
    }

    override fun initScreen() {
        val topicDetail = intent.getParcelableExtra<TopicDetailModel>(KEY_TOPIC_DETAIL)
        topicDetail?.let { viewModel.getTopicDetail(topicDetail) }
    }

    override fun bindData() {
        super.bindData()
        viewModel.detailLiveData.observe(this) {
            Log.e("TAG", "Detail $it")
        }
    }
}