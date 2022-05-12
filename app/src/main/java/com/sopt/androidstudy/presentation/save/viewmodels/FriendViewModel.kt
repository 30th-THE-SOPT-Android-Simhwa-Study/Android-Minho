package com.sopt.androidstudy.presentation.save.viewmodels

import androidx.lifecycle.*
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.types.MBTI
import com.sopt.androidstudy.data.model.types.MBTIFeatures
import com.sopt.androidstudy.domain.repository.FriendRepository
import com.sopt.androidstudy.presentation.util.Event
import com.sopt.androidstudy.presentation.util.safeValueOf
import kotlinx.coroutines.launch
import javax.inject.Inject

class FriendViewModel @Inject constructor(private val repository: FriendRepository) : ViewModel() {

    val friends = repository.getAllFriends()
    val friend = MutableLiveData<Friend?>()
    val switchFunction = MutableLiveData<Boolean>(false)
    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()
    val inputMBTI = MutableLiveData<String?>()
    val isInsertSuccess = MutableLiveData<Boolean>()
    private val _showToast = MutableLiveData<Event<Boolean>>()
    val showToast: LiveData<Event<Boolean>> = _showToast

    init {
        initSetting()
    }


    fun saveOrUpdate() {
        if (!inputName.value.isNullOrBlank() && !inputEmail.value.isNullOrBlank() && !inputMBTI.value.isNullOrBlank()) {
            val name = inputName.value!!
            val email = inputEmail.value!!
            val mbti = inputMBTI.value!!
            if (switchFunction.value == false) {
                insert(Friend(0, name, email, safeValueOf<MBTI>(mbti.uppercase())))
            } else {
                update(
                    Friend(
                        friend.value!!.id,
                        name,
                        email,
                        safeValueOf<MBTI>(mbti.uppercase())
                    )
                )
            }
            initSetting()
        }
    }

    private fun onStartEvent() {
        _showToast.value = Event(true)
    }

    fun clearAllOrDelete() {
        if (switchFunction.value == false) clearAll() else {
            delete(friend = friend.value!!)
        }
        initSetting()
    }

    private fun insert(friend: Friend) {
        viewModelScope.launch {
            isInsertSuccess.value = repository.insert(friend)
            onStartEvent()
        }

    }

    private fun update(friend: Friend) {
        viewModelScope.launch {
            repository.update(friend)
        }
    }

    private fun delete(friend: Friend) {
        viewModelScope.launch {
            repository.delete(friend)
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun selectFriend(friend: Friend) {
        this.friend.value = friend
        switchFunction.value = true
        inputEmail.value = friend.email
        inputName.value = friend.name
        inputMBTI.value = friend.mbti.toString()
    }

    private fun initSetting() {
        inputName.value = null
        inputEmail.value = null
        inputMBTI.value = null
        switchFunction.value = false
    }

}
