package com.example.fitnessproject.ui.activities.main

import android.app.Application
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.domain.usecase.main.MainUseCase
import com.example.fitnessproject.domain.usecase.main.MainUseCaseImpl
import com.example.fitnessproject.ui.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {
    private val mainUseCase: MainUseCase =
        MainUseCaseImpl((application as FitnessApplication).topicRepository)
}