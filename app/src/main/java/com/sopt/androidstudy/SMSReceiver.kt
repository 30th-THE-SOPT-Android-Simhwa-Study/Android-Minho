package com.sopt.androidstudy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import com.sopt.androidstudy.presentation.broad.BroadcastPracticeActivity

class SMSReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if ("android.provider.Telephony.SMS_RECEIVED" == intent?.action) {
            val bundle = intent.extras
            val message = parseMessage(bundle)
            Log.d("MeessageRRRRR", message[0].toString())

            if (message.isNotEmpty()) {
                val contents = message[0]?.messageBody.toString()
                //Log.e("contents", contents)
                sendToActivity(context, contents)
            }
        }
    }

    private fun sendToActivity(context: Context?, str: String) {
        val intent = Intent(context, BroadcastPracticeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("data", str)
        context?.startActivity(intent)
    }

    private fun parseMessage(bundle: Bundle?): Array<SmsMessage?> {
        val objs = bundle?.get("pdus") as Array<*>
        val messages = arrayOfNulls<SmsMessage>(objs.size)

        for (i in messages.indices) {
            val format = bundle.getString("format")
            messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
        }
        return messages
    }
}