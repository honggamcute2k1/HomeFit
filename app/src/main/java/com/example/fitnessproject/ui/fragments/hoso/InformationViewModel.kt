package com.example.fitnessproject.ui.fragments.hoso

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.data.local.entity.User
import com.example.fitnessproject.domain.usecase.user.UserUseCase
import com.example.fitnessproject.domain.usecase.user.UserUseCaseImpl
import com.example.fitnessproject.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class InformationViewModel(application: Application) : BaseViewModel(application) {
    val userUseCase: UserUseCase =
        UserUseCaseImpl((application as FitnessApplication).userRepository)

    val userInfoLiveData = MutableLiveData<User>()

    override fun onCreate() {
        super.onCreate()
        getUserInformation()
    }

    private fun getUserInformation() {
        showLoading(isShowLoading = true)
        myScope.launch {
            val user = withContext(Dispatchers.IO) {
                userUseCase.getAllUser().first()
            }
            userInfoLiveData.value = user
            showLoading(isShowLoading = false)
        }
    }
}