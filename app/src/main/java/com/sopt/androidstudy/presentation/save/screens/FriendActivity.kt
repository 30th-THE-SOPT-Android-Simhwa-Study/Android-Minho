package com.sopt.androidstudy.presentation.save.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.model.UserData
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.db.FriendDatabase
import com.sopt.androidstudy.data.model.db.FriendRepository
import com.sopt.androidstudy.databinding.ActivityMainBinding
import com.sopt.androidstudy.databinding.ActivitySaveBinding
import com.sopt.androidstudy.presentation.save.screens.adapter.FriendRecyclerViewAdapter
import com.sopt.androidstudy.presentation.save.viewmodels.FriendViewModel
import com.sopt.androidstudy.presentation.util.FriendViewModelFactory

class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveBinding
    private lateinit var friendViewModel: FriendViewModel
    private lateinit var friendAdapter: FriendRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.getParcelableExtra<UserData>("userData")
        //로그인시 내 계정 정보 받아오기. 아직은 안씀
        initDatabaseViewModel()
        initBindingView()
        displayFriendsList()
    }

    private fun initDatabaseViewModel() {
        val dao = FriendDatabase.getInstance(applicationContext).friendDAO
        val repository = FriendRepository(dao)
        val factory = FriendViewModelFactory(repository)
        friendViewModel = ViewModelProvider(this, factory).get(FriendViewModel::class.java)
    }

    private fun initBindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_save)
        binding.myViewModel = friendViewModel
        binding.lifecycleOwner = this
        friendAdapter = FriendRecyclerViewAdapter()
        friendAdapter.itemOnClickListener = object : FriendRecyclerViewAdapter.onItemClickListener {
            override fun onItemClick(
                position: Int
            ) {
                friendViewModel.saveOrUpdateButtonText.value = "업데이트"
                friendViewModel.clearAllOrDeleteButtonText.value = "삭제"
                friendViewModel.position.value = position
            }
        }
        friendViewModel.showToast.observe(this) {
            it.getContentIfNotHandled()?.let {
                if (friendViewModel.isValid.value == true)
                    Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show() else Toast.makeText(
                    this,
                    "exception : invalid email type",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        friendViewModel.friends.value?.let { friendAdapter.friendData.addAll(it) }
        binding.mainRcv.adapter = friendAdapter
    }


    private fun displayFriendsList() {
        friendViewModel.friends.observe(this) {
            friendAdapter.submitList(it)
        }
    }
}