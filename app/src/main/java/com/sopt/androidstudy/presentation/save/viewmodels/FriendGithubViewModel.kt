package com.sopt.androidstudy.presentation.save.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.sopt.androidstudy.data.remote.ServiceCreator
import com.sopt.androidstudy.data.remote.github.models.ResponseFollowing
import com.sopt.androidstudy.data.remote.github.models.ResponseRepo
import com.sopt.androidstudy.data.remote.github.models.ResponseUser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.stateIn
import retrofit2.Call
import retrofit2.Response

class FriendGithubViewModel : ViewModel() {
    private val userName = MutableLiveData<String>()

    fun setUserName(name: String) {
        userName.value = name
    }

    val getUserData: StateFlow<ResponseUser?> = flow {
        while (true) {
            if (!userName.value.isNullOrBlank()) {
                val responseUser = withContext(Dispatchers.IO) {
                    ServiceCreator.githubService.getUser(
                        "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",
                        userName.value.toString()
                    )
                }.body()
                if (responseUser != null) {
                    emit(responseUser)
                }
                Log.d("user : emit!!", responseUser.toString())
            }
            delay(2000)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

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
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val getFollower: Flow<List<ResponseFollowing>?> = flow {
        if (!userName.value.isNullOrBlank()) {
            val responseFollowing = withContext(Dispatchers.IO) {
                ServiceCreator.githubService.getFollowing(
                    "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",
                    userName.value.toString()
                )
            }.body()
            if (responseFollowing != null) {
                emit(responseFollowing)
                Log.d("follow : emit!!", responseFollowing.toString())
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}