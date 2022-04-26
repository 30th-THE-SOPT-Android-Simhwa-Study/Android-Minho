package com.sopt.androidstudy.presentation.sign.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivityLogin2Binding
import com.sopt.androidstudy.presentation.sign.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogin2Binding
    private val myLoginViewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login2)
        binding.lifecycleOwner = this@LoginActivity
        binding.loginviewmodel = myLoginViewModel

    }
}