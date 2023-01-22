package com.sopt.androidstudy.domain.repository

import com.sopt.androidstudy.domain.util.ApiResult
import java.util.ArrayList

interface LottoRepository {
    suspend fun getLottoNumbers(round: Int): ApiResult<ArrayList<Int>>
}