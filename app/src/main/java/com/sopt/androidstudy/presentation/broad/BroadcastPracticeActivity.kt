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

    /*private lateinit var smsBroadCastReceiver: SMSReceiver
    private lateinit var smsFilter: IntentFilter*/
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
/* 동적 리시버 사용 안함
    override fun onStart() {
        super.onStart()
        smsBroadCastReceiver = SMSReceiver()
        smsFilter = IntentFilter().apply {
            addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
            priority = IntentFilter.SYSTEM_HIGH_PRIORITY
        }
        registerReceiver(smsBroadCastReceiver, smsFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(smsBroadCastReceiver)
    }*/

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