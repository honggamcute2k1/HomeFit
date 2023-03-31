package com.example.fitnessproject.ui.activities.editinformation

import android.app.Application
import com.example.fitnessproject.data.local.entity.User
import com.example.fitnessproject.ui.fragments.hoso.InformationViewModel
import kotlinx.coroutines.launch

class EditInformationViewModel(application: Application) : InformationViewModel(application) {

    var user: User? = null

    fun updateUser(user: User) {
        showLoading(isShowLoading = true)
        myScope.launch {
            userUseCase.updateUser(user)
            showLoading(isShowLoading = false)
        }
    }
}