package com.example.fitnessproject.data.local.repository

import android.content.Context
import com.example.fitnessproject.FitnessApplication
import com.example.fitnessproject.domain.model.Gender

class SharePreferenceImpl(application: FitnessApplication) : SharePreference {
    private val sharePreference =
        application.getSharedPreferences(SharePreference.KEY_PREFER, Context.MODE_PRIVATE)

    companion object {

        @Volatile
        private var INSTANCE: SharePreferenceImpl? = null

        fun getSharePreference(application: FitnessApplication): SharePreferenceImpl {
            return INSTANCE ?: synchronized(this) {
                val instance = SharePreferenceImpl(application)
                INSTANCE = instance
                instance
            }
        }
    }

    override fun saveSetUpFirstTime(isSetup: Boolean) {
        sharePreference.edit().putBoolean(SharePreference.KEY_SETUP_FIRST_TIME, isSetup).apply()
    }

    override fun isSetupFirstTime() =
        sharePreference.getBoolean(SharePreference.KEY_SETUP_FIRST_TIME, false)

    override fun saveUserId(userId: Int) {
        sharePreference.edit().putInt(SharePreference.KEY_ID_USER, userId).apply()
    }

    override fun getUserId() = sharePreference.getInt(SharePreference.KEY_ID_USER, -1)

    override fun saveGender(gender: Int) {
        sharePreference.edit().putInt(SharePreference.KEY_GENDER, gender).apply()
    }

    override fun getGender() =
        Gender.valueOf(sharePreference.getInt(SharePreference.KEY_GENDER, -1))
}