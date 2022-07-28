package com.sopt.androidstudy.presentation.save.screens.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.androidstudy.data.remote.github.models.ResponseRepo
import com.sopt.androidstudy.databinding.ItemRepositoryIndexBinding

class RepoAdapter : ListAdapter<ResponseRepo, RepoAdapter.MyViewHolder>(RepoDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRepositoryIndexBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyViewHolder(private val binding: ItemRepositoryIndexBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ResponseRepo) {
            binding.repositoryName.text = data.name
            binding.repositoryInfo.text = data.full_name
        }
    }

    companion object RepoDiffUtil : DiffUtil.ItemCallback<ResponseRepo>() {

        override fun areItemsTheSame(
            oldItem: ResponseRepo,
            newItem: ResponseRepo
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseRepo,
            newItem: ResponseRepo
        ): Boolean {
            return oldItem == newItem
        }
    }
}