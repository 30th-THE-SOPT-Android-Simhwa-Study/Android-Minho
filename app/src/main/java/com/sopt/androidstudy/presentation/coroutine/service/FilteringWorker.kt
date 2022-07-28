package com.sopt.androidstudy.presentation.coroutine.service

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class FilteringWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        try {
            for (i in 0 until 300) {
                Log.i("LEE", "Filetering $i")
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}