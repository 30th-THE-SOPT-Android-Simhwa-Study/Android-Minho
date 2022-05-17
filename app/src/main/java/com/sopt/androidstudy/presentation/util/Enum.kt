package com.sopt.androidstudy.presentation.util

import com.sopt.androidstudy.data.model.types.MBTI

inline fun <reified T : Enum<MBTI>> safeValueOf(type: String?): MBTI? {
    if (type == null) return null
    return try {
        java.lang.Enum.valueOf(MBTI::class.java, type)
    } catch (e: IllegalArgumentException) {
        null
    }
}