package com.sopt.androidstudy.presentation.save.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.databinding.ItemFriendRecyclerviewBinding

class FriendRecyclerViewAdapter :
    ListAdapter<Friend, FriendRecyclerViewAdapter.FriendViewHolder>(FriendDiffUtil) {

    var friendData = mutableListOf<Friend>()

    var itemOnClickListener: onItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    /*fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        submitList(newList)
    }*/

    fun removeItem(position: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }

    inner class FriendViewHolder(private val binding: ItemFriendRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Friend) {
            with(binding) {
                friend = item
                layoutItem.setOnClickListener {
                    itemOnClickListener?.onItemClick(
                        adapterPosition
                    )
                }
            }
        }

    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }


    object FriendDiffUtil : DiffUtil.ItemCallback<Friend>() {

        override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem.id == newItem.id
        }
    }
}