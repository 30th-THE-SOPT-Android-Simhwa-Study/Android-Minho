package com.sopt.androidstudy.presentation.home.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel() {
    val content = MutableLiveData<String>()
}