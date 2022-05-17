package com.sopt.androidstudy.presentation.coroutine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivityCoroutineExampleBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class CoroutineExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutineExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            progressBar.visibility = View.INVISIBLE
            btnDownload.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch { // 코루틴의 메인 영역
                    progressBar.visibility = View.VISIBLE
                    val url = binding.etUrl.text.toString()
                    val bitmap = withContext(Dispatchers.IO) {
                        loadImage(url) // bitmap을 꺼내서 저장. 백그라운드 실행
                    }
                    imageView.setImageBitmap(bitmap)
                    progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }
    private suspend fun loadImage(imageUrl:String) : Bitmap {
        return withContext(Dispatchers.IO) {
            val url = URL(imageUrl)
            val stream = url.openStream()
            BitmapFactory.decodeStream(stream)
        }
    }
}