package com.example.fitnessproject.data.local

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

object TimeConverter {
    private val sdf = SimpleDateFormat("yyyy/mm/dd")

    @TypeConverter
    fun fromDateTimeString(value: String?): Date? {
        return value?.let { sdf.parse(it) }
    }

    @TypeConverter
    fun dateTimeToString(date: Date?): String? {
        return date?.let { sdf.format(it) }
    }
}