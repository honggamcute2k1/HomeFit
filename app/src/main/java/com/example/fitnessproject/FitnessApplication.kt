package com.example.fitnessproject

import android.app.Application
import android.util.Log
import com.example.fitnessproject.data.local.database.FitnessDatabase
import com.example.fitnessproject.data.local.repository.UserRepositoryImpl
import com.example.fitnessproject.ui.BaseViewModel
import kotlinx.coroutines.*

class FitnessApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database: FitnessDatabase by lazy { FitnessDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserRepositoryImpl(database.userDao()) }
    override fun onCreate() {
        super.onCreate()
    }
}