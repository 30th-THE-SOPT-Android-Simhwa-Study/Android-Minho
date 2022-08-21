package com.sopt.androidstudy.presentation.broad

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sopt.androidstudy.SMSReceiver
import com.sopt.androidstudy.databinding.ActivityBroadcastPracticeBinding


class BroadcastPracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBroadcastPracticeBinding
    private lateinit var br: SMSReceiver
    private lateinit var filter: IntentFilter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requirePerms()
        intent = getIntent()
        processedIntent(intent)
        clickEvent()
    }
    private fun requirePerms() {
        val permissions = arrayOf<String>(Manifest.permission.RECEIVE_SMS)
        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, permissions, 1)
        }
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
        br = SMSReceiver()
        filter = IntentFilter().apply {
            //addAction("org.android.androidsubeen")
            addAction("com.sopt.androidsturdy")
            addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
            priority = IntentFilter.SYSTEM_HIGH_PRIORITY
        }
        registerReceiver(br, filter)
    }

    override fun onPause() {
        Log.d("onPause", "Call")
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(br)
    }

    //받은 문자 - 문자열 set
    private fun processedIntent(intent: Intent?) {
        val str = intent?.getStringExtra("data")
        binding.etContent.setText(str)
    }

    private fun clickEvent() {
        binding.btnSend.setOnClickListener {
            Intent().also { intent ->
                intent.action = "com.sopt.androidstudy"
                intent.putExtra("data", "notiBroadCast")
                sendBroadcast(intent)
            }
        }
    }

    //앱이 종료되지 않은 상태에서 메시지를 받을경우 Receiver 에서 startActivity에 의해 onNewIntent가 실행됨.
    override fun onNewIntent(intent: Intent?) {
        Log.d("Message New", intent.toString())
        processedIntent(intent)
        super.onNewIntent(intent)
    }
}