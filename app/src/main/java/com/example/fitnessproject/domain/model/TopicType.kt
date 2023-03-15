package com.example.fitnessproject.domain.model

enum class TopicType(val type: Int, val resource: Int) {
    TOAN_THAN(1, -1),
    CO_BUNG(2, -1),
    TAP_MONG(3, -1),
    TAP_CHAN(4, -1);


    companion object {
        fun valueOf(type: Int): TopicType? {
            return values().firstOrNull { it.type == type }
        }
    }
}