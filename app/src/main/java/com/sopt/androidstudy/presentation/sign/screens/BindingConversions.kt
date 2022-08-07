package com.sopt.androidstudy.presentation.sign.screens

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.sopt.androidstudy.R
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URISyntaxException

object BindingConversions {
    @JvmStatic
    @BindingAdapter("visible")
    fun isVisible(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.INVISIBLE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter("load")
    fun loadImage(view: ImageView, url: String) {
        url.let {
            CoroutineScope(Dispatchers.Main).launch {
                view.load(url) {
                    placeholder(R.drawable.ic_launcher_foreground)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    /*@JvmStatic
    private lateinit var socket: Socket

    @JvmStatic
    fun get(): Socket {
        try {
            socket = IO.socket("http://10.0.2.2:8000")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        return socket
    }*/
}