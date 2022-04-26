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
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        binding.mainViewModel = _viewModel
        val intent = intent
        val user = intent.getParcelableExtra<UserData>("userData")
        Log.d("User", user?.uid.toString())
        Log.d("Password", user?.password.toString())
        user?.let { _viewModel.setUserData(it) }
    }
}