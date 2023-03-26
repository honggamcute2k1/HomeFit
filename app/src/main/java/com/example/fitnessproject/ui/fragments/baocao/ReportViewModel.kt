package com.example.fitnessproject.ui.fragments.baocao

import android.app.Application
import android.util.Log
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
    val weightListLiveData = MutableLiveData<List<UserInformationModel>>()

    var year: Int? = null
    var month: Int? = null

    override fun onCreate() {
        super.onCreate()
        getAllWeightInfoInTime()
    }

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

    fun getAllWeightInfoInTime(
        year: Int? = null,
        month: Int? = null
    ) {
        showLoading(isShowLoading = true)
        val startDate = Calendar.getInstance()
        val currentMonth = month ?: startDate.get(Calendar.MONTH)
        val currentYear = year ?: startDate.get(Calendar.YEAR)
        this.month = currentMonth
        this.year = currentYear
        val startDay = 1
        startDate.set(currentYear, currentMonth, startDay)
        val endDay = startDate.getActualMaximum(Calendar.DAY_OF_MONTH)
        val endDate = Calendar.getInstance()
        endDate.set(currentYear, currentMonth, endDay)
        myScope.launch {
            Log.e("TAG", "startDate $startDate")
            Log.e("TAG", "endDate $endDate")
            val weightList =
                userUserCase.getWeightOfUserInTime(startDate.time, endDate.time).filterNotNull()
            weightListLiveData.value = weightList
            showLoading(isShowLoading = false)
        }
    }
}