package com.sopt.androidstudy.data.util

interface BaseMapper<F,T> {
    fun map(from: F): T
}