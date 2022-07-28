package com.sopt.androidstudy.presentation.coroutine.service

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        try {
            val data = inputData.getInt("Hi", 10)
            for (i in 0 until data) {
                Log.i("LEE", "Upload $i")
            }
            val time = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            val currentDate = time.format(Date())
            val outPutData = Data.Builder()
                .putString("Hello", currentDate)
                .build()
            return Result.success(outPutData)
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}