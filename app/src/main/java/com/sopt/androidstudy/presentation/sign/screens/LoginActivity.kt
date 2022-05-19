package com.sopt.androidstudy.presentation.sign.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.model.UserData
import com.sopt.androidstudy.databinding.ActivityLogin2Binding
import com.sopt.androidstudy.presentation.home.screens.MainActivity
import com.sopt.androidstudy.presentation.save.screens.FriendActivity
import com.sopt.androidstudy.presentation.sign.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogin2Binding
    private val myLoginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login2)
        binding.lifecycleOwner = this@LoginActivity
        binding.loginviewmodel = myLoginViewModel
        myLoginViewModel.getIsClick().observe(this) {
            if (it) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(
                        "userData", UserData(
                            myLoginViewModel.getUserEmail().value,
                            myLoginViewModel.getUserName().value,
                            myLoginViewModel.getUserPassword().value
                        )
                    )
                }
                startActivity(intent)
                finish()
            }
        }
    }
}