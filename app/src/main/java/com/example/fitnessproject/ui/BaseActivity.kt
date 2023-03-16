package com.example.fitnessproject.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.fitnessproject.R
import kotlinx.android.synthetic.main.base_activity.*
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity(), CoreInterface {
    lateinit var viewModel: V
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        this.layoutInflater.inflate(getLayoutId(), baseView, true)
        Glide.with(this).load(R.drawable.loading).into(imgLoading)
        viewModel = ViewModelProviders
            .of(this)[(this::class.java.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<V>]
        initScreen()
        bindData()
    }

    override fun bindData() {
        viewModel.loadingData.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isShow: Boolean) {
        frame_loading?.showOrGone(isShow = isShow)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}

interface CoreInterface {
    fun getLayoutId(): Int
    fun initScreen()
    fun bindData()
}

fun View.showOrGone(isShow: Boolean) {
    val visibility = if (isShow) View.VISIBLE else View.GONE
    this.visibility = visibility
}