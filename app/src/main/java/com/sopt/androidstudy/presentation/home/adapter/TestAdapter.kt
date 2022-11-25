package com.sopt.androidstudy.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.androidstudy.databinding.ItemTestBinding

class TestAdapter(private val clickTest: (String) -> Unit) :
    RecyclerView.Adapter<TestAdapter.TestViewHolder>() {
    private val tests = mutableListOf<String>()

    class TestViewHolder(private val binding: ItemTestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(text: String) {
            binding.textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTestBinding.inflate(layoutInflater, parent, false)
        return TestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.itemView.setOnClickListener { clickTest(tests[position]) }
        holder.onBind(tests[position])
    }

    override fun getItemCount() = tests.size

    fun initContent(content: List<String>) {
        tests.addAll(content)
        notifyDataSetChanged()
    }
}