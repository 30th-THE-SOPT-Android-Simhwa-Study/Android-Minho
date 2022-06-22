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
import com.sopt.androidstudy.presentation.home.viewmodels.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ReceiveEventRecyclerViewAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        binding.mainViewModel = viewModel
        adapter = ReceiveEventRecyclerViewAdapter()
        binding.rcvMain.adapter = adapter
        binding.rcvMain.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager(this).orientation
            )
        )
        displayReceiveData()
        val user = intent.getParcelableExtra<UserData>("userData")
        //user?.name?.let {
        getList("KkamSonLee")
        //}
    }

    private fun getList(userName: String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collectLatest {
                    viewModel.receiveData.value = it
                }
            }
        }
    }

    private fun displayReceiveData() {
        viewModel.getReceiveData().observe(this) {
            adapter.submitList(it)
        }
    }

}