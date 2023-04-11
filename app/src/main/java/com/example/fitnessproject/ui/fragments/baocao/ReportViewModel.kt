package com.example.fitnessproject.ui.fragments.baocao

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.domain.model.LevelBMI
import com.example.fitnessproject.domain.model.TopicDetailSelectedModel
import com.example.fitnessproject.domain.model.UserInformationModel
import com.example.fitnessproject.domain.usecase.main.MainUseCase
import com.example.fitnessproject.domain.usecase.main.MainUseCaseImpl
import com.example.fitnessproject.domain.usecase.user.UserUseCase
import com.example.fitnessproject.domain.usecase.user.UserUseCaseImpl
import com.example.fitnessproject.ui.BaseViewModel
import kotlinx.coroutines.*
import java.util.*

class ReportViewModel(application: Application) : BaseViewModel(application) {
    private val userUserCase: UserUseCase =
        UserUseCaseImpl((application as FitnessApplication).userRepository)
    private val mainUseCase: MainUseCase =
        MainUseCaseImpl(
            (application as FitnessApplication).topicRepository,
            application.apiRepository
        )

    val isAddWeightLiveData = MutableLiveData<Boolean>()
    val weightListLiveData = MutableLiveData<List<UserInformationModel>>()
    val topicDetailSelectedLiveData = MutableLiveData<List<TopicDetailSelectedModel>>()

    var year: Int? = null
    var month: Int? = null

    override fun onCreate() {
        super.onCreate()
        getDataInTime()
        getInformationBMI(LevelBMI.BEO_1)
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

    fun getDataInTime(
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
            val weightDeferred = async(Dispatchers.IO) {
                delay(1500)
                userUserCase.getWeightOfUserInTime(
                    startDate.time,
                    endDate.time
                )
            }
            val topicDetailSelectedDeferred = async(Dispatchers.IO) {
                mainUseCase.getTopicDetailSelectedInTime(
                    startDate.time,
                    endDate.time
                )
            }
            weightListLiveData.value = weightDeferred.await().filterNotNull()
            topicDetailSelectedLiveData.value =
                topicDetailSelectedDeferred.await().distinctBy { it.timeDoIt }
            showLoading(isShowLoading = false)
        }
    }

    fun updateBMI(height: Float, weight: Float) {
        showLoading(isShowLoading = true)
        myScope.launch {
            val user = withContext(Dispatchers.IO) {
                userUserCase.getAllUser().first()
            }
            user.weight = weight.toDouble()
            user.height = height.toDouble()
            userUserCase.updateUser(user)
        }
    }

    fun getInformationBMI(type: LevelBMI) {
        showLoading(isShowLoading = true)
        myScope.launch {
            val bmiResponse = mainUseCase.getBMIResponse()
            Log.e("TAG", "BMI $bmiResponse")
        }
        showLoading(false)
    }

    fun getCurrentDateString(): String {
        val calendar = Calendar.getInstance()
        return calendar.time.toDateString()
    }
}