package com.sopt.androidstudy.presentation.chatting

import android.os.Bundle
import androidx.activity.viewModels
import com.sopt.androidstudy.presentation.base.BaseActivity
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivitySocketChattingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SocketChattingActivity : BaseActivity<ActivitySocketChattingBinding>(R.layout.activity_socket_chatting) {

    private val viewModel : ChattingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}