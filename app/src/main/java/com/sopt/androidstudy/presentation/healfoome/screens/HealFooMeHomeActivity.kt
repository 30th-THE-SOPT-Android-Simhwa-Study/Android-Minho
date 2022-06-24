package com.sopt.androidstudy.presentation.healfoome.screens

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivityHealFooMeHomeBinding
import com.sopt.androidstudy.databinding.FragmentNaverMapBinding
import com.sopt.androidstudy.presentation.healfoome.viewmodels.HealfooMeViewModel

class HealFooMeHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHealFooMeHomeBinding
    private lateinit var myViewModel: HealfooMeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealFooMeHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myViewModel = ViewModelProvider(this)[HealfooMeViewModel::class.java]
        myViewModel.setHealfooList(
            listOf(
                BaseMarker(
                    position = LatLng(37.5349, 127.1234),
                    icon = MarkerIcons.BLACK,
                    iconTintColor = Color.RED,
                    captionText = "샐러디",
                    tag = "marker1",
                    restaurantName = "샐러디",
                    mainMenu = "아보카도 덮밥",
                    isCalorie = true,
                    tipDescription = "알아서 잘 드세요"
                ),
                BaseMarker(
                    position = LatLng(37.5349, 127.1230),
                    captionText = "살 빠지는 식당",
                    tag = "살 빠지는 식당",
                    restaurantName = "살 빠지는 식당",
                    mainMenu = "보라색 카레",
                    isCalorie = true,
                    tipDescription = "이걸 시키고도 먹을 수 있을까?"
                )
            )
        )
    }


}