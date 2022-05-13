package com.sopt.androidstudy.presentation.save.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.types.MBTIFeatures
import com.sopt.androidstudy.domain.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendDetailViewModel @Inject constructor(private val repository: FriendRepository) : ViewModel() {
    private val friend = MutableLiveData<Friend?>()

    fun setFriend(friend: Friend) {
        this.friend.value = friend
    }

    fun getFriend(): LiveData<Friend?> = friend

    fun getMBTIFeatures(): List<MBTIFeatures>? =
        friend.value?.mbti?.let { repository.getMBTIFeatures(it) }

    companion object {
        private const val TAG = "FriendDetailViewModel"
    }
}