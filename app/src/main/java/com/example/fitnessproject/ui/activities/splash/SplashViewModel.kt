package com.example.fitnessproject.ui.activities.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.domain.usecase.user.UserUseCaseImpl
import com.example.fitnessproject.domain.usecase.version.VersionUserCaseImpl
import com.example.fitnessproject.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(application: Application) : BaseViewModel(application) {
    private val userUserCase = UserUseCaseImpl((application as FitnessApplication).userRepository)
    private val versionUserCase =
        VersionUserCaseImpl((application as FitnessApplication).versionRepository)

    val initSuccessLiveData = MutableLiveData<Boolean>()

    fun initDataBaseFirstTime() {
        showLoading(isShowLoading = true)
        viewModelScope.launch {
            delay(3000)
            val versionDefer = withContext(Dispatchers.IO) {
                versionUserCase.getVersion()
            }
            initSuccessLiveData.value = true
            showLoading(isShowLoading = false)
        }
    }
}