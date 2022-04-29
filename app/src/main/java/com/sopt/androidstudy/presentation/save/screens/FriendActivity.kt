package com.sopt.androidstudy.presentation.save.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.model.db.FriendDatabase
import com.sopt.androidstudy.data.model.db.FriendRepository
import com.sopt.androidstudy.databinding.ActivityMainBinding
import com.sopt.androidstudy.databinding.ActivitySaveBinding
import com.sopt.androidstudy.presentation.save.viewmodels.FriendViewModel
import com.sopt.androidstudy.presentation.util.FriendViewModelFactory

class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveBinding
    private lateinit var friendViewModel: FriendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_save)

        val dao = FriendDatabase.getInstance(application).friendDAO
        val repository = FriendRepository(dao)
        val factory = FriendViewModelFactory(repository)

        friendViewModel = ViewModelProvider(this, factory).get(FriendViewModel::class.java)

        binding.myViewModel = friendViewModel
        binding.lifecycleOwner = this

        displayFriendsList()
    }

    private fun displayFriendsList(){
        friendViewModel.friends.observe(this, Observer{
            Log.i("LEE", it.toString())
        })
    }
}