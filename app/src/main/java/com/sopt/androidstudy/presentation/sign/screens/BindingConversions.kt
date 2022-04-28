package com.sopt.androidstudy.presentation.sign.screens

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingConversions {
    @JvmStatic
    @BindingAdapter("visible")
    fun isVisible(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.INVISIBLE else View.VISIBLE
    }
}