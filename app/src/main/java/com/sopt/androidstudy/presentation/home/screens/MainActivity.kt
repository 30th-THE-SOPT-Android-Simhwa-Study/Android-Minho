package com.sopt.androidstudy.presentation.home.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.model.UserData
import com.sopt.androidstudy.data.remote.ServiceCreator
import com.sopt.androidstudy.databinding.ActivityMainBinding
import com.sopt.androidstudy.presentation.home.adapter.ReceiveEventRecyclerViewAdapter
import com.sopt.androidstudy.presentation.home.adapter.TestAdapter
import com.sopt.androidstudy.presentation.home.viewmodels.MyViewModel
import com.sopt.androidstudy.presentation.home.viewmodels.TestViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TestAdapter
    private val testViewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initInfo()
    }

    private fun clickTest(content: String) {
        testViewModel.content.value = content
    }

    private fun initAdapter() {
        adapter = TestAdapter { clickTest(it) }
        binding.rvTest.adapter = adapter
    }

    private fun initInfo() {
        adapter.initContent(
            listOf(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6"
            )
        )
    }
}