package com.example.fitnessproject.ui.activities.gender

import android.util.Log
import com.example.fitnessproject.R
import com.example.fitnessproject.ui.BaseActivity

class ChooseGenderActivity : BaseActivity<GenderViewModel>() {

    override fun getLayoutId() = R.layout.activity_choose_gender

    override fun initScreen() {
        Log.e("TAG", "Choose gender")
    }
}