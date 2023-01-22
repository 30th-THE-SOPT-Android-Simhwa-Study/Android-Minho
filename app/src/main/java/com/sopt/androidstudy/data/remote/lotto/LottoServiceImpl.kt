package com.sopt.androidstudy.data.remote.lotto

import com.google.gson.JsonParser
import java.net.URL

class LottoServiceImpl : LottoService {
    override suspend fun getLottoNumbers(round: Int): ArrayList<Int> {
        val url = "https://dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=$round"
        val lottoNumbers = ArrayList<Int>()
        try {
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
                lottoNumbers.add(round)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return lottoNumbers
    }
}