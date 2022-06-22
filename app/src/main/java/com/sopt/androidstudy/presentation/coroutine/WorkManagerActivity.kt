package com.sopt.androidstudy.presentation.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.work.*
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivityWorkManagerBinding
import com.sopt.androidstudy.presentation.coroutine.service.*
import java.text.SimpleDateFormat
import java.util.*

class WorkManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkManagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            setOneTimeWorkRequest()
        }
    }


    private fun setOneTimeWorkRequest() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val myWorkManager = WorkManager.getInstance(this)

        val data: Data = Data.Builder()
            .putInt("Hi", 125)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .addTag("upload")
            .build()

        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()

        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()

        val deleteRequest = OneTimeWorkRequest.Builder(DeleteWorker::class.java)
            .build()
        val downloadingWorker = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()

        val chain1 = myWorkManager.beginWith(compressingRequest)
            .then(downloadingWorker)
        val chain2 = myWorkManager.beginWith(filteringRequest)
            .then(deleteRequest)
        val chain3 =
            WorkContinuation.combine(listOf(chain1, chain2)).then(uploadRequest)
        chain3.enqueue()
        myWorkManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this) {
            binding.textView2.text = it.state.name
            if (it.state.isFinished) {
                if (it.id.toString() == "upload") {
                    val outputData = it.outputData.getString("Hello") ?: ""
                    Toast.makeText(
                        applicationContext,
                        outputData,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}