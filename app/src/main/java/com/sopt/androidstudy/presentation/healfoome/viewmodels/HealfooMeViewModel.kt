package com.sopt.androidstudy.presentation.healfoome.viewmodels

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import com.sopt.androidstudy.presentation.healfoome.screens.BaseMarker

class HealfooMeViewModel : ViewModel() {
    private val healfooList = MutableLiveData<List<Marker>>()
    private val healfooDescriptionList = MutableLiveData<List<BaseMarker>>()
    private val selectMarker = MutableLiveData<BaseMarker>(
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
        )
    )

    fun getSelectMarker(): LiveData<BaseMarker> = selectMarker
    fun getHealfooList(): LiveData<List<Marker>> = healfooList
    fun setSelectMarker(tag: String) {
        selectMarker.value = healfooDescriptionList.value?.find {
            it.tag == tag
        }
    }

    fun setHealfooList(markerList: List<BaseMarker>) {
        val tempList = mutableListOf<Marker>()
        healfooDescriptionList.value = markerList
        markerList.forEach {
            val marker = Marker().apply {
                icon = it.icon
                iconTintColor = it.iconTintColor
                tag = it.tag
                position = it.position
                captionText = it.captionText
            }
            tempList.add(marker)
        }
        healfooList.value = tempList
    }
}