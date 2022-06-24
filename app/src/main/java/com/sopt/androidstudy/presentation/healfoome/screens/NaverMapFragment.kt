package com.sopt.androidstudy.presentation.healfoome.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.sopt.androidstudy.databinding.FragmentNaverMapBinding
import com.sopt.androidstudy.presentation.healfoome.viewmodels.HealfooMeViewModel


class NaverMapFragment : Fragment(), OnMapReadyCallback, Overlay.OnClickListener {
    private var _binding: FragmentNaverMapBinding? = null
    val binding get() = _binding!!
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private lateinit var infoWindow: InfoWindow
    private val healfooMeViewModel: HealfooMeViewModel by activityViewModels<HealfooMeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNaverMapBinding.inflate(inflater, container, false)
        initNaverMapSettings()
        locationSource =
            FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        return binding.root
    }

    private fun initMarker(healfooList: List<Marker>?) {
        if (healfooList?.isNotEmpty() == true) {
            healfooList.forEach { marker ->
                marker.map = naverMap
                marker.onClickListener = this
            }
        } else {
            Log.d("Hi", "Null!")
        }
    }

    private fun observingMarker() {
        healfooMeViewModel.getHealfooList().observe(viewLifecycleOwner) {
            initMarker(it)
        }
    }

    private fun initNaverMapSettings() {
        binding.myMap.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions,
                grantResults
            )
        ) {
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
        initMarker(healfooMeViewModel.getHealfooList().value)
        infoWindow = InfoWindow()
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return infoWindow.marker?.tag as CharSequence? ?: ""
            }
        }
        naverMap.setOnSymbolClickListener {
            Log.d("Marker", it.toString())
            true
        }
        observingMarker()
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

    override fun onClick(p0: Overlay): Boolean {
        Log.d("MARKER!!!", p0.tag.toString())
        if (p0 is Marker) {
            infoWindow.open(p0)
        }
        return true
    }
}