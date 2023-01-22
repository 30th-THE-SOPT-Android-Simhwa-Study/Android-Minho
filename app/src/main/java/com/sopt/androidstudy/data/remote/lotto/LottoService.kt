package com.sopt.androidstudy.data.remote.lotto

import java.util.ArrayList

interface LottoService {
    suspend fun getLottoNumbers(round:Int): ArrayList<Int>
}