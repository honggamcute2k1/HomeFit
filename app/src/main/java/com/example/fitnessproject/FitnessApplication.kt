package com.example.fitnessproject

import android.app.Application
import com.example.fitnessproject.data.local.database.FitnessDatabase
import com.example.fitnessproject.data.local.repository.TopicRepositoryImpl
import com.example.fitnessproject.data.local.repository.UserRepositoryImpl
import com.example.fitnessproject.data.local.repository.VersionRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FitnessApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database: FitnessDatabase by lazy { FitnessDatabase.getDatabase(this, applicationScope) }
    val userRepository by lazy { UserRepositoryImpl(database.userDao()) }
    val versionRepository by lazy { VersionRepositoryImpl(database.versionDao()) }
    val topicRepository by lazy { TopicRepositoryImpl(database.topicDao()) }

}