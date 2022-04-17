package com.sopt.androidstudy.presentation.home.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivityMainBinding
import com.sopt.androidstudy.presentation.home.viewmodels.MyViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        binding.viewModel = viewModel
        viewModel.getCount().observe(this){

        }

    }
}