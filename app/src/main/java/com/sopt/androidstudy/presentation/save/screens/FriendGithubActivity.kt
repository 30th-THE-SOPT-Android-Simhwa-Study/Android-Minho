package com.sopt.androidstudy.presentation.save.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.remote.ServiceCreator
import com.sopt.androidstudy.databinding.ActivityFriendGithubBinding
import com.sopt.androidstudy.presentation.save.screens.fragment.Follwer
import com.sopt.androidstudy.presentation.save.screens.fragment.Repo
import com.sopt.androidstudy.presentation.save.viewmodels.FriendGithubViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FriendGithubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendGithubBinding
    private val myFragments =
        listOf(Follwer(), Repo())
    lateinit var user: String
    var isEnabled = true
    val friendGithubViewModel: FriendGithubViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = intent.getStringExtra("username").toString()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_github)
        user.let {
            friendGithubViewModel.setUserName(user)
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    friendGithubViewModel.getUserData.collect {
                        binding.user = it
                    }
                }
            }
        }
        bindingView()
    }

    private fun bindingView() {

        binding.btnFollower.isEnabled = isEnabled
        binding.btnRepo.isEnabled = !isEnabled

        supportFragmentManager.beginTransaction()
            .add(R.id.fragContainerGithub, myFragments[0])
            .commit()
        binding.btnFollower.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainerGithub, myFragments[0])
                .commit()
            isEnabled = false
            binding.btnFollower.isEnabled = isEnabled
            binding.btnRepo.isEnabled = !isEnabled
        }
        binding.btnRepo.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainerGithub, myFragments[1])
                .commit()
            isEnabled = true
            binding.btnFollower.isEnabled = isEnabled
            binding.btnRepo.isEnabled = !isEnabled
        }
    }
}