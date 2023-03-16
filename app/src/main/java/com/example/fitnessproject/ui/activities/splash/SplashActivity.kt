package com.example.fitnessproject.ui.activities.splash

import android.content.Intent
import com.example.fitnessproject.R
import com.example.fitnessproject.ui.BaseActivity
import com.example.fitnessproject.ui.activities.gender.ChooseGenderActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity<SplashViewModel>() {
    override fun getLayoutId() = R.layout.activity_splash
    override fun initScreen() {
        viewModel.initDataBaseFirstTime()

    }

    override fun bindData() {
        super.bindData()
        viewModel.initSuccessLiveData.observe(this) {
            startActivity(Intent(this@SplashActivity, ChooseGenderActivity::class.java))
            finish()
        }
    }
}