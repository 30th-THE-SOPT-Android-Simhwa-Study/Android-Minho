package com.sopt.androidstudy.presentation.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val count = MutableLiveData(0)

    fun increase() {
        count.value = count.value?.plus(1)
    }
    fun getCount(): LiveData<Int> = count
}