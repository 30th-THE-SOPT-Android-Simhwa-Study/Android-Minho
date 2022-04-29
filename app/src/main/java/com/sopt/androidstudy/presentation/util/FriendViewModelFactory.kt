package com.sopt.androidstudy.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.androidstudy.data.model.db.FriendRepository
import com.sopt.androidstudy.presentation.save.viewmodels.FriendViewModel

class FriendViewModelFactory(private val repository: FriendRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java)) {
            return FriendViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}