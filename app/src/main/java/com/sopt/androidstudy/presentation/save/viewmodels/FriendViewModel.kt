package com.sopt.androidstudy.presentation.save.viewmodels

import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.db.FriendRepository
import com.sopt.androidstudy.presentation.util.Event
import kotlinx.coroutines.launch

class FriendViewModel(private val repository: FriendRepository) : ViewModel() {

    val friends = repository.friends
    val position = MutableLiveData<Int?>()
    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    val isValid = MutableLiveData<Boolean>()
    private val _showToast = MutableLiveData<Event<Boolean>>()
    val showToast: LiveData<Event<Boolean>> = _showToast

    init {
        initSetting()
    }

    fun saveOrUpdate() {
        if (!inputName.value.isNullOrBlank() && !inputEmail.value.isNullOrBlank()) {
            val name = inputName.value!!
            val email = inputEmail.value!!
            if (position.value == null) {
                insert(Friend(0, name, email))
            } else {
                update(Friend(friends.value!!.get(position.value!!).id, name, email))
                initSetting()
            }

        }
    }

    fun onStartEvent() {
        _showToast.value = Event(true)
    }

    fun clearAllOrDelete() {
        if (position.value == null) clearAll() else {
            delete(friends.value!!.get(position.value!!))
            initSetting()
        }
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
