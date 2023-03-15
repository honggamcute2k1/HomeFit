package com.example.fitnessproject.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val userId: Int? = null,

    @ColumnInfo(name = "user_name")
    val name: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "gender")
    val gender: Int,

    @ColumnInfo(name = "height")
    val height: Double,

    @ColumnInfo(name = "weight")
    val weight: Double
)