package com.sopt.androidstudy.presentation.save.screens.fragment


import android.os.Bundle
import android.util.Log
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
import com.sopt.androidstudy.databinding.FragmentRepoBinding
import com.sopt.androidstudy.presentation.save.viewmodels.FriendGithubViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Repo : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel by activityViewModels<FriendGithubViewModel>()

    private lateinit var adapter: RepoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        bindingView()
        return binding.root
    }

    private fun bindingView() {
        adapter = RepoAdapter()
        binding.recyclerRepo.adapter = adapter
        binding.recyclerRepo.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
        )
        displayFollowingList()
    }

    private fun displayFollowingList() {
        /*lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.getRepository.collect {
                    adapter.submitList(it)
                }
            }

        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}