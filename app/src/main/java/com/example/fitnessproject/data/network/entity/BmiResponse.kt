package com.example.fitnessproject.data.network.entity

import com.google.gson.Gson

data class BmiResponse(
    val items: List<BmiItem>
) {
    companion object {
        fun fromJson(json: String): BmiResponse {
            return Gson().fromJson(json, BmiResponse::class.java)
        }
    }
}

data class BmiItem(
    val title: String?,
    val level: Int,
    val reason: List<String>?,
    val fix: List<String>?
)