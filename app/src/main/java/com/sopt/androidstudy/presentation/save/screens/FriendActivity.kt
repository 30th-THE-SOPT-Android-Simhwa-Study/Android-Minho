package com.sopt.androidstudy.presentation.save.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.datasources.FriendDataSources
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.db.FriendDatabase
import com.sopt.androidstudy.data.repository.FriendRepositoryImpl
import com.sopt.androidstudy.databinding.ActivitySaveBinding
import com.sopt.androidstudy.presentation.save.adapter.FriendRecyclerViewAdapter
import com.sopt.androidstudy.presentation.save.viewmodels.FriendViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveBinding
    private val friendViewModel: FriendViewModel by viewModels()
    private lateinit var friendAdapter: FriendRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val user = intent.getParcelableExtra<UserData>("userData")
        //로그인시 내 계정 정보 받아오기. 아직은 안씀
        initBindingView()
        displayFriendsList()
    }


    private fun initBindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_save)
        binding.myViewModel = friendViewModel
        binding.lifecycleOwner = this
        friendAdapter = FriendRecyclerViewAdapter(::selectFriend)
        initEvent()
        friendViewModel.friends.value?.let { friendAdapter.submitList(it) }
        binding.mainRcv.adapter = friendAdapter
    }

    private fun initEvent() {
        friendViewModel.showToast.observe(this) {
            it.getContentIfNotHandled()?.let {
                if (friendViewModel.isInsertSuccess.value == true)
                    Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show() else Toast.makeText(
                    this,
                    "exception : invalid email type",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun selectFriend(friend: Friend) {
        friendViewModel.selectFriend(friend = friend)
        //이전 기능 포함
        val intent = Intent(this@FriendActivity, FriendDetailActivity::class.java).apply {
            putExtra("friend", friendViewModel.friend.value)
        }
        startActivity(intent)
    }

    private fun displayFriendsList() {
        friendViewModel.friends.observe(this) {
            friendAdapter.submitList(it)
        }
    }
}