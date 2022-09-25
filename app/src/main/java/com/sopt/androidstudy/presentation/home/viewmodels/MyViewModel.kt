package com.sopt.androidstudy.presentation.home.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.androidstudy.data.remote.ServiceCreator
import com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class MyViewModel : ViewModel() {
    val receiveData = MutableLiveData<List<ResponseReceiveEvent>>()

    fun getReceiveData(): LiveData<List<ResponseReceiveEvent>> = receiveData
    fun setReceiveData(data: List<ResponseReceiveEvent>) {
        receiveData.value = data
    }

    /*val stateFlow = flow {
        while (true) {
            val responseEvent = withContext(Dispatchers.IO) {
                ServiceCreator.githubService.getReceiveEvents("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","KkamSonLee")
            }.body()?.filter {
                it.type == "PushEvent"
            }
            emit(responseEvent)
            delay(5000)
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), null
    )*/
}