package com.sopt.androidstudy.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEvent
import com.sopt.androidstudy.databinding.ItemEventListBinding
import com.sopt.androidstudy.databinding.ItemFriendRecyclerviewBinding
import com.sopt.androidstudy.presentation.save.adapter.FriendRecyclerViewAdapter

class ReceiveEventRecyclerViewAdapter() :
    ListAdapter<ResponseReceiveEvent, ReceiveEventRecyclerViewAdapter.EventViewHolder>(
        EventDiffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EventViewHolder(
        private val binding: ItemEventListBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseReceiveEvent) {
            with(binding) {
                event = item
            }
        }
    }

    companion object EventDiffUtil : DiffUtil.ItemCallback<ResponseReceiveEvent>() {

        override fun areItemsTheSame(
            oldItem: ResponseReceiveEvent,
            newItem: ResponseReceiveEvent
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseReceiveEvent,
            newItem: ResponseReceiveEvent
        ): Boolean {
            return oldItem == newItem
        }
    }
}