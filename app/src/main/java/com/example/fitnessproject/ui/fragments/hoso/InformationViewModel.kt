package com.example.fitnessproject.ui.fragments.hoso

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.data.local.entity.User
import com.example.fitnessproject.domain.model.Day
import com.example.fitnessproject.domain.model.Reminder
import com.example.fitnessproject.domain.usecase.user.UserUseCase
import com.example.fitnessproject.domain.usecase.user.UserUseCaseImpl
import com.example.fitnessproject.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

open class InformationViewModel(application: Application) : BaseViewModel(application) {
    val userUseCase: UserUseCase =
        UserUseCaseImpl((application as FitnessApplication).userRepository)

    val userInfoLiveData = MutableLiveData<User>()
    val reminderLiveData = MutableLiveData<Reminder>()
    var reminder: Reminder? = null

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

    fun getReminder() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        reminder =
            sharePreference.getReminder() ?: Reminder(
                hour = hour,
                minute = minute,
                days = mutableListOf<Day>().apply {
                    add(Day(Reminder.ID_MONDAY, Calendar.MONDAY))
                    add(Day(Reminder.ID_TUESDAY, Calendar.TUESDAY))
                    add(Day(Reminder.ID_WEDNESDAY, Calendar.WEDNESDAY))
                    add(Day(Reminder.ID_THURSDAY, Calendar.THURSDAY))
                    add(Day(Reminder.ID_FRIDAY, Calendar.FRIDAY))
                    add(Day(Reminder.ID_SATURDAY, Calendar.SATURDAY))
                    add(Day(Reminder.ID_SUNDAY, Calendar.SUNDAY))
                }
            )
        reminderLiveData.value = reminder
    }

    fun saveReminder() {
        reminder?.let { sharePreference.saveReminder(it) }
    }
}