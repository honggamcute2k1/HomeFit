package com.example.fitnessproject.data.local.repository

interface SharePreference {
    companion object {
        const val KEY_PREFER = "KEY_PREFER"
        const val KEY_SETUP_FIRST_TIME = "KEY_SETUP_FIRST_TIME"

        const val KEY_ID_USER = "KEY_ID_USER"
    }

    fun saveSetUpFirstTime(isSetup: Boolean)
    fun isSetupFirstTime(): Boolean

    fun saveUserId(userId: Int)
    fun getUserId(): Int
}