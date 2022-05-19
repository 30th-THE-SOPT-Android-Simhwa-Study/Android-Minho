package com.sopt.androidstudy.presentation.sign.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val isEmailValid = MutableLiveData<Boolean>(false)
    private val isPasswordValid = MutableLiveData<Boolean>(false)
    private val userEmail = MutableLiveData<String>()
    private val userName = MutableLiveData<String>()
    private val userPassword = MutableLiveData<String>()
    private val isEnabledButton = MediatorLiveData<Boolean>()
    private val is_click = MutableLiveData<Boolean>(false)

    init {
        initEnabledButton()

    }

    private fun initEnabledButton() {
        isEnabledButton.addSource(isEmailValid) {
            isEnabledButton.value = combineAuthValid(it, isPasswordValid.value)
        }
        isEnabledButton.addSource(isPasswordValid) {
            isEnabledButton.value = combineAuthValid(isEmailValid.value, it)
        }
    }

    fun onEmailTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        userEmail.value = s.toString().trim()
        checkEmailFormat()
    }

    fun onNameTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        userName.value = s.toString().trim()
    }

    fun onPasswordTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        userPassword.value = s.toString().trim()
        checkPasswordFormat()
    }

    private fun checkEmailFormat() {
        val emailPattern = Patterns.EMAIL_ADDRESS
        isEmailValid.value = emailPattern.matcher(userEmail.value).matches()
    }

    private fun checkPasswordFormat() {
        val passwordPattern =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{7,15}.$")
        isPasswordValid.value = passwordPattern.matcher(userPassword.value).matches()
    }

    private fun combineAuthValid(emailValid: Boolean?, passwordValid: Boolean?): Boolean {
        if (emailValid == null || passwordValid == null) {
            return false
        }
        return emailValid == true && passwordValid == true
    }

    fun onClick() {
        is_click.value = true
    }

    fun getIsClick(): LiveData<Boolean> = is_click
    fun getUserEmail(): LiveData<String> = userEmail
    fun getUserPassword(): LiveData<String> = userPassword
    fun getUserName(): LiveData<String> = userName
    fun getEnabledButton(): LiveData<Boolean> = isEnabledButton
    fun getEmailValid(): LiveData<Boolean> = isEmailValid
    fun getPasswordValid(): LiveData<Boolean> = isPasswordValid
}
