package com.example.fitnessproject.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var myScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    val loadingData = MutableLiveData<Boolean>()

    fun showLoading(isShowLoading: Boolean) {
        loadingData.value = isShowLoading
    }

    fun onStart() {
        myScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    }

    fun onStop() {
        myScope.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        myScope.cancel()
    }
}
