package com.sopt.androidstudy.data.repository

import com.sopt.androidstudy.data.remote.lotto.LottoService
import com.sopt.androidstudy.domain.repository.LottoRepository
import com.sopt.androidstudy.domain.util.ApiResult
import java.util.ArrayList
import javax.inject.Inject

class LottoRepositoryImpl @Inject constructor(private val lottoService: LottoService) : LottoRepository {
    override suspend fun getLottoNumbers(round: Int): ApiResult<ArrayList<Int>> {
        val result = lottoService.getLottoNumbers(round)
        return if (result.size == 0) {
            ApiResult.Failure("Not Yet")
        } else ApiResult.Success(result)
    }
}