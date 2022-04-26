package com.sopt.androidstudy.presentation.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.androidstudy.data.model.UserData

class MyViewModel : ViewModel() {
    private val userData = MutableLiveData(UserData("", ""))


    fun getUserData(): LiveData<UserData> = userData
    fun setUserData(data: UserData) {
        userData.value = data
    }
}