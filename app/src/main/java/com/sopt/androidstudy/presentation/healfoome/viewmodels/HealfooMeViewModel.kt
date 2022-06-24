package com.sopt.androidstudy.presentation.healfoome.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naver.maps.map.overlay.Marker
import com.sopt.androidstudy.presentation.healfoome.screens.BaseMarker

class HealfooMeViewModel : ViewModel() {
    private val healfooList = MutableLiveData<List<Marker>>()


    fun getHealfooList(): LiveData<List<Marker>> = healfooList
    fun setHealfooList(markerList: List<BaseMarker>) {
        val tempList = mutableListOf<Marker>()
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