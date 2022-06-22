package com.sopt.androidstudy.presentation.healfoome.screens

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.sopt.androidstudy.databinding.FragmentNaverMapBinding


class NaverMapFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentNaverMapBinding? = null
    val binding get() = _binding!!
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNaverMapBinding.inflate(inflater, container, false)
        initNaverMapSettings()
        locationSource =
        FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        return binding.root
    }

    private fun initNaverMapSettings() {
        binding.myMap.getMapAsync(this);
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(p0: NaverMap) {
        naverMap = p0
        naverMap.locationSource = locationSource
        naverMap.uiSettings.isLocationButtonEnabled = true

        val marker = Marker()
        val marker2 = Marker()
        marker.position = LatLng(37.5349, 127.1234)
        marker.icon = MarkerIcons.BLACK
        marker.iconTintColor = Color.RED
        marker2.position = LatLng(37.5349, 127.1230)
        marker.map = p0
        marker2.map = p0
        val infoWindow = InfoWindow()
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return infoWindow.marker?.tag as CharSequence? ?:""
            }
        }
        marker.tag = "marker1"
        marker2.tag = "marker2"
        marker.setOnClickListener {
            marker.captionText = "marker1"
            infoWindow.open(marker)
            true
        }
        marker2.setOnClickListener {
            marker2.captionText = "marker2"
            infoWindow.open(marker2)
            true
        }
        //배경 지도 선택
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myMap.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.myMap.onDestroy()
    }
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}