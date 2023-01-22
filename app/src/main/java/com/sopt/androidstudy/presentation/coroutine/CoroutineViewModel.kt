package com.sopt.androidstudy.presentation.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.androidstudy.domain.repository.LottoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CoroutineViewModel @Inject constructor(
    private val repository: LottoRepository,
) : ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job
    val lottoNum = CoroutineScope(coroutineContext).async {
        repository.getLottoNumbers(1035)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}