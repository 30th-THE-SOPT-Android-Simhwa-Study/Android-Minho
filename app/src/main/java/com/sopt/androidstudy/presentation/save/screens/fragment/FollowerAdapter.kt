package com.sopt.androidstudy.presentation.save.screens.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.androidstudy.data.remote.github.models.ResponseFollowing
import com.sopt.androidstudy.databinding.ItemSampleListBinding

class FollowerAdapter :
    ListAdapter<ResponseFollowing, FollowerAdapter.MyViewHolder>(FollowerDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemSampleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyViewHolder(private val binding: ItemSampleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseFollowing) {
            with(binding) {
                follower = data
            }
        }
    }

    companion object FollowerDiffUtil : DiffUtil.ItemCallback<ResponseFollowing>() {

        override fun areItemsTheSame(
            oldItem: ResponseFollowing,
            newItem: ResponseFollowing
        ): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(
            oldItem: ResponseFollowing,
            newItem: ResponseFollowing
        ): Boolean {
            return oldItem == newItem
        }
    }
}