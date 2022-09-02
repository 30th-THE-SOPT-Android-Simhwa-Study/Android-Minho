package com.sopt.androidstudy.presentation.broad

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        processedIntent(intent)
        clickEvent()
    }

    private fun requirePerms() {
        val permissions = arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS)
        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
        val permissionCheck2 =
            ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        if (permissionCheck == PackageManager.PERMISSION_DENIED || permissionCheck2 == PackageManager.PERMISSION_DENIED) {
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
        val phoneNumber = intent?.getStringExtra("phoneNumber") ?: ""
        var message = intent?.getStringExtra("message") ?: ""
        if(intent?.getIntExtra("flag",0) == 1){
            message = message.replace("[^0-9]".toRegex(),"")
        }
        with(binding) {
            etContent.setText(message)
            etPhone.setText(phoneNumber)
        }
    }

    private fun clickEvent() {
        binding.btnSend.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(
                binding.etPhone.text.toString(),
                null,
                binding.etContent.text.toString(),
                null,
                null
            )
        }
    }

    //앱이 종료되지 않은 상태에서 메시지를 받을경우 Receiver 에서 startActivity에 의해 onNewIntent가 실행됨.
    override fun onNewIntent(intent: Intent) {
        intent.putExtra("flag", 1)
        processedIntent(intent)
        super.onNewIntent(intent)
    }
}