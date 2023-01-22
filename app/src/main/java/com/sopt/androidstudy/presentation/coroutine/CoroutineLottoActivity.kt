package com.sopt.androidstudy.presentation.coroutine

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.androidstudy.databinding.ActivityCoroutineLottoBinding
import com.sopt.androidstudy.domain.util.ApiResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

@AndroidEntryPoint
class CoroutineLottoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutineLottoBinding
    var job: Job? = null
    private val coroutineViewModel: CoroutineViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineLottoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.generatebutton.setOnClickListener {
            val myNumber = createLottoNumbers()
            updateLottoBallImage(myNumber)
            job = CoroutineScope(Dispatchers.Main.immediate).launch {
                when (val data = coroutineViewModel.lottoNum.await()) {
                    is ApiResult.Success -> {
                        binding.tvWinning.text =
                            "$myNumber + ${myLottoRank(myNumber, data.datas)}"
                    }
                    is ApiResult.Failure -> {
                        Timber.e(data.throwable)
                    }
                }
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
        job?.cancel()
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
}