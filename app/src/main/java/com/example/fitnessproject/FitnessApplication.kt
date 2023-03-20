package com.example.fitnessproject

import android.app.Application
import com.example.fitnessproject.data.local.database.FitnessDatabase
import com.example.fitnessproject.data.local.repository.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FitnessApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database: FitnessDatabase by lazy { FitnessDatabase.getDatabase(this, applicationScope) }
    val sharePreference: SharePreference by lazy { SharePreferenceImpl.getSharePreference(this) }
    val userRepository by lazy { UserRepositoryImpl(database.userDao()) }
    val versionRepository by lazy { VersionRepositoryImpl(database.versionDao()) }
    val topicRepository by lazy {
        TopicRepositoryImpl(
            database.topicDao(),
            database.topicDetailDao()
        )
    }
}