package com.example.fitnessproject.ui.activities.gender

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.data.local.entity.User
import com.example.fitnessproject.domain.model.Gender
import com.example.fitnessproject.domain.usecase.user.UserUseCaseImpl
import com.example.fitnessproject.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class GenderViewModel(application: Application) : BaseViewModel(application) {
    private val userUserCase = UserUseCaseImpl((application as FitnessApplication).userRepository)

    val setupUserLiveData = MutableLiveData<Boolean>()

    fun chooseGender(gender: Gender) {
        showLoading(isShowLoading = true)
        viewModelScope.launch {
            delay(1000)
            withContext(Dispatchers.IO) {
                val newUser = User(
                    name = UUID.randomUUID().toString(),
                    password = "",
                    gender = gender.gender,
                    height = 0.0,
                    weight = 0.0,
                    phoneNumber = "",
                    born = 1980,
                    fullName = ""
                )
                userUserCase.insertUser(newUser)
                sharePreference.saveSetUpFirstTime(isSetup = true)
            }
            setupUserLiveData.value = true
            showLoading(isShowLoading = false)
        }
    }
}