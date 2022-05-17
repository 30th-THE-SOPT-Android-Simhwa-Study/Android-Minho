package com.sopt.androidstudy.presentation.save.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.datasources.FriendDataSources
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.db.FriendDatabase
import com.sopt.androidstudy.data.repository.FriendRepositoryImpl
import com.sopt.androidstudy.databinding.ActivityFriendDetailBinding
import com.sopt.androidstudy.presentation.save.viewmodels.FriendDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendDetailBinding
    private val friendDetailViewModel: FriendDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBindingView()
    }


    private fun initBindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_detail)
        binding.viewModel = friendDetailViewModel
        binding.lifecycleOwner = this
        val friend = intent.getParcelableExtra<Friend>("friend")
        friend?.let {
            friendDetailViewModel.setFriend(friend)
        }
    }

}