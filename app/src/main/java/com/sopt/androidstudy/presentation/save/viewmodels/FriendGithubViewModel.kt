package com.sopt.androidstudy.presentation.save.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.androidstudy.data.remote.ServiceCreator
import com.sopt.androidstudy.data.remote.github.models.ResponseFollowing
import com.sopt.androidstudy.data.remote.github.models.ResponseRepo
import com.sopt.androidstudy.data.remote.github.models.ResponseUser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.stateIn
import kotlin.concurrent.thread

class FriendGithubViewModel : ViewModel() {
    private val userName = MutableLiveData<String>()

    fun setUserName(name: String) {
        userName.value = name
    }

    private val _selectUser = MutableLiveData<ResponseFollowing>()
    val selectUser: LiveData<ResponseFollowing> = _selectUser

    fun selectFollower(data: ResponseFollowing) {
        _selectUser.value = data
    }

    /* val getUserData: StateFlow<ResponseUser?> = flow {
         if (!userName.value.isNullOrBlank()) {
             val responseUser = withContext(Dispatchers.IO) {
                 ServiceCreator.githubService.getUser(
                     "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",
                     userName.value.toString()
                 )
             }.body()
             if (responseUser != null) {
                 emit(responseUser)
                 userData()
             }
             Log.d("user : emit!!", responseUser.toString())
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)*/

    /*fun userData(): String? = getUserData.value?.avatar_url
    val getRepository: StateFlow<List<ResponseRepo>?> = flow {
        if (!userName.value.isNullOrBlank()) {
            val responseRepository = withContext(Dispatchers.IO) {
                ServiceCreator.githubService.getRepository(
                    "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",
                    userName.value.toString()
                )
            }.body()
            if (responseRepository != null) {
                emit(responseRepository)
                Log.d("repo : emit!!", responseRepository.toString())
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)*/

    /*val getFollower: StateFlow<List<ResponseFollowing>?> = flow {
        if (!userName.value.isNullOrBlank()) {
            val responseFollowing =
                ServiceCreator.githubService.getFollowing(
                    "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",
                    userName.value.toString()
                )
            if (responseFollowing.isSuccessful) {
                emit(responseFollowing.body())
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)*/
}