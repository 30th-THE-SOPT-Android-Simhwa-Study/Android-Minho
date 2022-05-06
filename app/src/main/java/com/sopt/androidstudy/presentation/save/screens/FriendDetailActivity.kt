package com.sopt.androidstudy.presentation.save.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.datasources.FriendDataSoures
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.db.FriendDatabase
import com.sopt.androidstudy.data.repository.FriendRepositoryImpl
import com.sopt.androidstudy.databinding.ActivityFriendDetailBinding
import com.sopt.androidstudy.presentation.save.viewmodels.FriendDetailViewModel
import com.sopt.androidstudy.presentation.save.viewmodels.FriendDetailViewModelFactory

class FriendDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendDetailBinding
    private lateinit var friendDetailViewModel: FriendDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDatabaseViewModel()
        initBindingView()
    }

    private fun initDatabaseViewModel() {
        val dao = FriendDatabase.getInstance(applicationContext).friendDAO
        val dataSoures = FriendDataSoures(dao)
        val repoimpl = FriendRepositoryImpl(dataSoures)
        val factory = FriendDetailViewModelFactory(repoimpl)
        friendDetailViewModel =
            ViewModelProvider(this, factory).get(FriendDetailViewModel::class.java)
    }

    private fun initBindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_detail)
        binding.viewModel = friendDetailViewModel
        binding.lifecycleOwner = this
        val friend = intent.getParcelableExtra<Friend>("friend")
        if (friend != null) {
            friendDetailViewModel.setFriend(friend)
        }
    }

}