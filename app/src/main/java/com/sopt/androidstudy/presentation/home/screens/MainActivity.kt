package com.sopt.androidstudy.presentation.home.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.model.UserData
import com.sopt.androidstudy.databinding.ActivityMainBinding
import com.sopt.androidstudy.presentation.home.viewmodels.MyViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main).run {
            lifecycleOwner = this@MainActivity
            mainViewModel = viewModel
        }
        val user = intent.getParcelableExtra<UserData>("userData")
        user?.let { viewModel.setUserData(it) }
    }
}