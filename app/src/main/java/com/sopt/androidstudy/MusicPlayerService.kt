package com.sopt.androidstudy

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.sopt.androidstudy.presentation.musicplayer.MusicNotification

class MusicPlayerService : Service(),
    MediaPlayer.OnPreparedListener { // Service와 사용시 미디어 데이터를 가져오고 디코딩해야하는 경우 MediaPlayer 작업을 비동기 처리로 실행
    private lateinit var notification: Notification
    private var mediaPlayer: MediaPlayer? = null
    private var position = 0
    private val musicList = mutableListOf(R.raw.samples, R.raw.sample_2, R.raw.sample_3)
    private val musicTitle = listOf("one", "two", "three")

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "Action Received = ${intent?.action}")
        when (intent?.action) {
            Actions.PREV -> {
                position = --position
                if (position < 0) {
                    position = musicList.size - 1
                }
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(this, musicList[position])
                notification = MusicNotification.createNotification(this, musicTitle[position])
                startForeground(NOTIFICATION_ID, notification)
            }
            Actions.PLAY -> {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.pause()
                } else {
                    mediaPlayer?.start()
                }
            }
            Actions.NEXT -> {
                position = ++position % 3
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(this, musicList[position])
                mediaPlayer?.setOnPreparedListener(this@MusicPlayerService)
                notification = MusicNotification.createNotification(this, musicTitle[position])
                startForeground(NOTIFICATION_ID, notification)
            }
            Actions.START_FOREGROUND -> {
                startForegroundService()
            }
            Actions.STOP_FOREGROUND -> {
                stopForegroundService()
            }
        }
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun startForegroundService() {
        val notification = MusicNotification.createNotification(this, musicTitle[position % 3])
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun stopForegroundService() {
        stopForeground(true)
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate()")
        mediaPlayer = MediaPlayer.create(this, musicList[position]);
        mediaPlayer?.setOnPreparedListener(this@MusicPlayerService)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()  // mediaplayer 할당 해제
        mediaPlayer = null
        Log.e(TAG, "onDestroy()")
    }

    override fun onPrepared(p0: MediaPlayer?) {
        mediaPlayer?.start()
    }//근데 데이터 자체가 비동기로 오지 않아서.. 안써도 되긴할듯

    companion object {
        const val TAG = "[MusicPlayerService]"
        const val NOTIFICATION_ID = 20
    }

}