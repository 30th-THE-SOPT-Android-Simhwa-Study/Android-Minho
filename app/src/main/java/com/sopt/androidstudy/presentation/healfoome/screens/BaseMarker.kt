package com.sopt.androidstudy.presentation.healfoome.screens

import android.graphics.Color
import android.os.Parcelable
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MarkerIcons
import java.io.Serializable

data class BaseMarker(
    val position: LatLng,
    val icon: OverlayImage = MarkerIcons.BLACK,
    val iconTintColor: Int = Color.GREEN,
    val tag: String,
    val captionText: String,
    val restaurantName: String,
    val mainMenu: String,
    val isCalorie: Boolean,
    val tipDescription: String
) : Serializable
