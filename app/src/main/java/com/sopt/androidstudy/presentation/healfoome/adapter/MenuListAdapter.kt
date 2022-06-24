package com.sopt.androidstudy.presentation.healfoome.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ItemMenuBinding

class MenuListAdapter(private val myMenuList: ArrayList<Int>) :
    RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {


    class ViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.ivMenu.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        //binding.root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        //처리 해주거나, 아이템 ViewGroup을 match_parent 다 해줘야함, recyclerview 본체가 없어서 그런듯
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myMenuList[position])
    }

    override fun getItemCount(): Int {
        return myMenuList.size
    }
}