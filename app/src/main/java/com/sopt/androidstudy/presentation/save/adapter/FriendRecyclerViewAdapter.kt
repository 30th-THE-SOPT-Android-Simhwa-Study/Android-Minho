package com.sopt.androidstudy.presentation.save.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.databinding.ItemFriendRecyclerviewBinding
import java.util.*
import javax.inject.Inject

class FriendRecyclerViewAdapter(private val itemClickListener: (Friend) -> Unit) :
    ListAdapter<Friend, FriendRecyclerViewAdapter.FriendViewHolder>(FriendDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FriendViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FriendViewHolder(
        private val binding: ItemFriendRecyclerviewBinding,
        private val itemClickListener: (Friend) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Friend) {
            with(binding) {
                friend = item
                layoutItem.setOnClickListener {
                    itemClickListener(item)
                }
            }
        }
    }

    companion object FriendDiffUtil : DiffUtil.ItemCallback<Friend>() {

        override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem == newItem
        }
    }
}