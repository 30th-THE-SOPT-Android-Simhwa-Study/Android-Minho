package com.sopt.androidstudy.presentation.chating

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.androidstudy.BR
import com.sopt.androidstudy.databinding.ItemLeftMessageBinding
import com.sopt.androidstudy.databinding.ItemRightMessageBinding
import com.sopt.androidstudy.presentation.model.chating.Chat

class ChatingMultiHolderAdapter :
    ListAdapter<Chat, RecyclerView.ViewHolder>(ChatDiffCallBack) {

    class FromViewHolder(val binding: ItemLeftMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    class ToViewHolder(val binding: ItemRightMessageBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            FROM -> FromViewHolder(
                ItemLeftMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TO -> ToViewHolder(
                ItemRightMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> FromViewHolder(
                ItemLeftMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            FROM -> (holder as FromViewHolder).binding.setVariable(BR.fromData, getItem(position))
            TO -> (holder as ToViewHolder).binding.setVariable(BR.toData, getItem(position))
        }
    }

    override fun getItemViewType(position: Int) =
        if (getItem(position).send)
            TO
        else
            FROM

    companion object {
        private const val FROM = 1
        private const val TO = 2
        private object ChatDiffCallBack: DiffUtil.ItemCallback<Chat>(){
            override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem.messageId == newItem.messageId
            }

            override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem == newItem
            }
        }
    }
}