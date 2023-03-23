package com.example.fitnessproject.ui.fragments.baocao

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.domain.model.UserInformationModel
import com.example.fitnessproject.domain.usecase.user.UserUseCase
import com.example.fitnessproject.domain.usecase.user.UserUseCaseImpl
import com.example.fitnessproject.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ReportViewModel(application: Application) : BaseViewModel(application) {
    private val userUserCase: UserUseCase =
        UserUseCaseImpl((application as FitnessApplication).userRepository)

    val isAddWeightLiveData = MutableLiveData<Boolean>()

    fun insertOrUpdateWeight(weight: Double, time: Date) {
        showLoading(isShowLoading = true)
        myScope.launch {
            val user = withContext(Dispatchers.IO) {
                userUserCase.getAllUser().first()
            }
            val userId = user.userId
            val userInformation = userUserCase.getWeightOfUserByTime(time)
            userInformation?.let { u ->
                u.weight = weight
                userUserCase.updateWeightForUser(u)
            } ?: userUserCase.insertWeightForUser(
                UserInformationModel(
                    weight = weight,
                    time = time,
                    userId = userId!!
                )
            )
            isAddWeightLiveData.value = true
            showLoading(isShowLoading = false)
        }
    }
}