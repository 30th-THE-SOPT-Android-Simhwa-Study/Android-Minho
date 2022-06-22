package com.sopt.androidstudy.presentation.healfoome.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivityHealFooMeHomeBinding
import com.sopt.androidstudy.databinding.FragmentNaverMapBinding

class HealFooMeHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHealFooMeHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealFooMeHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}