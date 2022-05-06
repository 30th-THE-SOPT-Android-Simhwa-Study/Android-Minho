package com.sopt.androidstudy.presentation.save.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.androidstudy.domain.repository.FriendRepository

class FriendViewModelFactory(private val repository: FriendRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java)) {
            return FriendViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}