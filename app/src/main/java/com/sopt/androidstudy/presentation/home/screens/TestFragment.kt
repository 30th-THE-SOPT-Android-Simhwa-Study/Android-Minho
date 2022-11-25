package com.sopt.androidstudy.presentation.home.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.FragmentTestBinding
import com.sopt.androidstudy.presentation.home.viewmodels.TestViewModel

class TestFragment : Fragment() {
    private var _binding: FragmentTestBinding? = null
    private val binding: FragmentTestBinding get() = requireNotNull(_binding) { "여기서 터짐" }
    private val testViewModel: TestViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTestBinding.inflate(layoutInflater)
        observeContent()
        return binding.root
    }

    private fun observeContent() {
        testViewModel.content.observe(viewLifecycleOwner) {
            binding.textView3.text = it
        }
    }

}