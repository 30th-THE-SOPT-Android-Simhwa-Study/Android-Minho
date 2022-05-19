package com.sopt.androidstudy.presentation.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.sopt.androidstudy.databinding.ActivityCoroutineLottoBinding
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class CoroutineLottoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutineLottoBinding
    private val job = Job()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineLottoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.generatebutton.setOnClickListener {
            val myNumber = createLottoNumbers()
            updateLottoBallImage(myNumber)
            CoroutineScope(Dispatchers.Main + job).launch {
                val data = getLottoNumbers()
                binding.tvWinning.text = "${myNumber} + ${myLottoRank(myNumber, data)}"
            }
        }
    }

    private fun myLottoRank(myNumber: ArrayList<Int>, todayNumber: ArrayList<Int>): String {
        var count = 0
        for (index in 0..5) {
            if (myNumber.contains(todayNumber[index])) count++
        }
        return when (count) {
            6 -> "1등"
            5 -> {
                if (myNumber.contains(todayNumber[6])) "2등"
                else "3등"
            }
            4 -> "4등"
            3 -> "5등"
            else -> "낙첨"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun createLottoNumbers(): ArrayList<Int> {
        val result = arrayListOf<Int>()
        val source = IntArray(45) { it + 1 }
        val seed =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.KOREA).format(Date()).hashCode()
                .toLong()
        val random = Random(seed)
        source.shuffle(random)
        source.slice(0..5).forEach { num ->
            result.add(num)
        }
        result.sort()
        return result
    }

    private fun getDrawableID(number: Int): Int {
        val number = String.format("%02d", number)
        val string = "ball_$number"
        return resources.getIdentifier(string, "drawable", packageName)
    }

    private fun updateLottoBallImage(result: ArrayList<Int>) {
        with(binding) {
            ivGame0.setImageResource(getDrawableID(result[0]))
            ivGame1.setImageResource(getDrawableID(result[1]))
            ivGame2.setImageResource(getDrawableID(result[2]))
            ivGame3.setImageResource(getDrawableID(result[3]))
            ivGame4.setImageResource(getDrawableID(result[4]))
            ivGame5.setImageResource(getDrawableID(result[5]))
        }
    }

    private suspend fun getLottoNumbers(): ArrayList<Int> {
        val round = "1000"
        val url = "https://dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=$round"
        val lottoNumbers = ArrayList<Int>()
        try {
            withContext(Dispatchers.IO) {
                val response = URL(url).readText()
                val jsonObject = JsonParser.parseString(response).asJsonObject
                val returnValue = jsonObject.get("returnValue").asString
                if (returnValue == "success") {
                    for (i in 1..6) {
                        val lottoNumber = jsonObject.get("drwtNo$i").asInt
                        lottoNumbers.add(lottoNumber)
                    }
                    val bonusNumber = jsonObject.get("bnusNo").asInt
                    lottoNumbers.add(bonusNumber)
                    lottoNumbers.add(round.toInt())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return lottoNumbers
    }

}