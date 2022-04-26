package com.sopt.androidstudy.presentation.sign.screens

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingConversions {


    @BindingAdapter("visible")
    @JvmStatic
    fun isVisible(view: TextView, is_enable: Boolean) {
        when (is_enable) {
            true -> {
                view.visibility = View.INVISIBLE

            }
            false -> {
                view.visibility = View.VISIBLE
            }
        }
    }

}