package com.sopt.androidstudy.presentation.chatting

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivitySocketChattingBinding
import com.sopt.androidstudy.domain.util.ApiResult
import com.sopt.androidstudy.presentation.base.BaseActivity
import com.sopt.androidstudy.presentation.mapper.chatting.ChattingListASCMapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SocketChattingActivity :
    BaseActivity<ActivitySocketChattingBinding>(R.layout.activity_socket_chatting) {

    private val viewModel: ChattingViewModel by viewModels()
    private lateinit var adapter: ChattingMultiHolderAdapter

    @Inject
    lateinit var mapper: ChattingListASCMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        onBind()
        collectList()

    }

    private fun collectList() {
        viewModel.chattingList.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is ApiResult.Idle -> {
                    Timber.e("Init!!!!!")
                }
                is ApiResult.Loading -> {
                    Timber.e("Loading!!!!!!!!")
                }
                is ApiResult.Success -> {
                    Timber.e("Success!!!!!!!!")
                    if (it.datas.isNotEmpty()) {
                        adapter.submitList(mapper.map(it.datas))
                        binding.rcChattingList.smoothScrollToPosition(adapter.itemCount - 1)
                    }
                }
                is ApiResult.Failure -> {
                    Timber.e("Failure!!!!!!!! ${it.throwable}")
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun onBind() {
        adapter = ChattingMultiHolderAdapter()
        binding.rcChattingList.adapter = adapter
    }
}