package com.example.fitnessproject.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "information")
data class UserInformation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "weight")
    val weight: Double?,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "user_id")
    val userId: Int
)