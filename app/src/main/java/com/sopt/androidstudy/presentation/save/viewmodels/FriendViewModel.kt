package com.sopt.androidstudy.presentation.save.viewmodels

import androidx.lifecycle.*
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.types.MBTI
import com.sopt.androidstudy.data.model.types.MBTIFeatures
import com.sopt.androidstudy.domain.repository.FriendRepository
import com.sopt.androidstudy.presentation.util.Event
import com.sopt.androidstudy.presentation.util.safeValueOf
import kotlinx.coroutines.launch

class FriendViewModel(private val repository: FriendRepository) : ViewModel() {

    val friends = repository.getAllFriends()
    val position = MutableLiveData<Int?>()
    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()
    val inputMBTI = MutableLiveData<String?>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    val isValid = MutableLiveData<Boolean>()
    private val _showToast = MutableLiveData<Event<Boolean>>()
    val showToast: LiveData<Event<Boolean>> = _showToast

    init {
        initSetting()
    }

    fun getMBTIFeatures(): List<MBTIFeatures>? =
        position.value?.let { friends.value?.get(it)?.mbti?.let { repository.getMBTIFeatures(it) } }


    fun saveOrUpdate() {
        if (!inputName.value.isNullOrBlank() && !inputEmail.value.isNullOrBlank()) {
            val name = inputName.value!!
            val email = inputEmail.value!!
            val mbti = inputMBTI.value!!
            if (position.value == null) {
                insert(Friend(0, name, email, safeValueOf<MBTI>(mbti.uppercase())))
            } /*else {
                update(
                    Friend(
                        friends.value!![position.value!!].id,
                        name,
                        email,
                        safeValueOf<MBTI>(mbti.toUpperCase())
                    )
                )
                initSetting()
            }*/

        }
    }

    fun onStartEvent() {
        _showToast.value = Event(true)
    }

    fun clearAllOrDelete() {
        if (position.value == null) clearAll() /*else {
            friends.value?.let { delete(it[position.value!!]) }
            initSetting()
        }*/
    }

    fun insert(friend: Friend) {
        viewModelScope.launch {
            isValid.value = repository.insert(friend)
            onStartEvent()
            initSetting()
        }

    }

    fun update(friend: Friend) {
        viewModelScope.launch {
            repository.update(friend)
        }
    }

    fun delete(friend: Friend) {
        viewModelScope.launch {
            repository.delete(friend)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    private fun initSetting() {
        saveOrUpdateButtonText.value = "저장"
        clearAllOrDeleteButtonText.value = "전체 삭제"
        position.value = null
        inputName.value = null
        inputEmail.value = null
    }
}
