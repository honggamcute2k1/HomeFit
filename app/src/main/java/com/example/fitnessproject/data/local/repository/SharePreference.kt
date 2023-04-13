package com.example.fitnessproject.data.local.repository

import com.example.fitnessproject.domain.model.Gender

interface SharePreference {
    companion object {
        const val KEY_PREFER = "KEY_PREFER"
        const val KEY_SETUP_FIRST_TIME = "KEY_SETUP_FIRST_TIME"

        const val KEY_ID_USER = "KEY_ID_USER"
        const val KEY_GENDER = "KEY_GENDER"
    }

    fun saveSetUpFirstTime(isSetup: Boolean)
    fun isSetupFirstTime(): Boolean

    fun saveUserId(userId: Int)
    fun getUserId(): Int

    fun saveGender(gender: Int)
    fun getGender(): Gender
}