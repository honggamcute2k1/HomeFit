package com.example.fitnessproject.ui.activities.topicdetail

import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.fitnessproject.R
import com.example.fitnessproject.domain.model.TopicModel
import com.example.fitnessproject.domain.model.TopicType
import com.example.fitnessproject.ui.BaseActivity
import com.example.fitnessproject.ui.activities.topicdetail.adapter.AdapterTopicDetail
import com.example.fitnessproject.widget.DividerItemDecoratorAll
import kotlinx.android.synthetic.main.activity_list_topic_detail.*

class ListTopicDetailActivity : BaseActivity<TopicDetailViewModel>() {
    override fun getLayoutId() = R.layout.activity_list_topic_detail

    companion object {
        const val KEY_TOPIC_MODEL = "KEY_TOPIC_MODEL"
    }

    override fun initScreen() {
        val topicModel = intent.getParcelableExtra<TopicModel>(KEY_TOPIC_MODEL)
        topicModel?.let {
            viewModel.getListTopicDetailOfTopic(topicModel = it)
            imageTopic?.setImageResource(TopicType.valueOf(it.type).resource)
        }

    }

    override fun bindData() {
        super.bindData()
        viewModel.topicDetailLiveData.observe(this) { list ->
            rcvTopicDetail?.apply {
                addItemDecoration(
                    DividerItemDecoratorAll(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.line_divider
                        )
                    )
                )
                adapter = AdapterTopicDetail(list = list,
                    onItemTopicDetailClicked = { model ->
                        val intent =
                            Intent(this@ListTopicDetailActivity, TopicDetailActivity::class.java)
                        intent.putExtra(TopicDetailActivity.KEY_TOPIC_DETAIL, model)
                        startActivity(intent)
                    })
            }

        }
    }
}