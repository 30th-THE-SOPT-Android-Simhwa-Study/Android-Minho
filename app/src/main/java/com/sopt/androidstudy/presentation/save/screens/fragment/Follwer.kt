package com.sopt.androidstudy.presentation.save.screens.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.androidstudy.databinding.FragmentFollwerBinding
import com.sopt.androidstudy.presentation.save.viewmodels.FriendGithubViewModel
import kotlinx.coroutines.launch

class Follwer : Fragment() {
    private var _binding: FragmentFollwerBinding? = null
    val binding get() = _binding!!
    private lateinit var adapter: FollowerAdapter
    private val profileViewModel by activityViewModels<FriendGithubViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollwerBinding.inflate(layoutInflater, container, false)
        bindingView()
        return binding.root

    }

    private fun bindingView() {
        adapter = FollowerAdapter()
        binding.recyclerFollower.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.recyclerFollower.adapter = adapter
        displayFollowingList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayFollowingList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.getFollower.collect {
                    adapter.submitList(it.body())
                }
            }

        }
    }
}