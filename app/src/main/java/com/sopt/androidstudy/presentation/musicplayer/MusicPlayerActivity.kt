package com.sopt.androidstudy.presentation.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mument_android.app.presentation.base.BaseActivity
import com.sopt.androidstudy.Actions
import com.sopt.androidstudy.MusicPlayerService
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivityMusicPlayerBinding

class MusicPlayerActivity : BaseActivity<ActivityMusicPlayerBinding>(R.layout.activity_music_player) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        binding.btnStart.setOnClickListener {
            val intent = Intent(this, MusicPlayerService::class.java)
            intent.action = Actions.START_FOREGROUND
            startService(intent)
        }
        binding.btnStop.setOnClickListener {
            val intent = Intent(this, MusicPlayerService::class.java)
            intent.action = Actions.STOP_FOREGROUND
            startService(intent)
        }
    }
}