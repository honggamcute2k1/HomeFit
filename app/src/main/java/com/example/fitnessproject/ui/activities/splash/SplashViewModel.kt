package com.example.fitnessproject.ui.activities.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.domain.usecase.user.UserUseCaseImpl
import com.example.fitnessproject.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(application: Application) : BaseViewModel(application) {
    private val userUserCase = UserUseCaseImpl((application as FitnessApplication).repository)

    fun getAllUser() {
        showLoading(isShowLoading = true)
        viewModelScope.launch {
            delay(3000)
            val users = withContext(Dispatchers.IO) {
                userUserCase.getAllUser()
            }
            Log.e("TAG", "user ${users.size}")
            showLoading(isShowLoading = false)
        }
    }
}