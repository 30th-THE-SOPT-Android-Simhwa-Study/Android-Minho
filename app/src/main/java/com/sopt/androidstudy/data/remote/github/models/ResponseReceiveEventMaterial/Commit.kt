package com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEventMaterial

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Commit(
    val message: String,
):Parcelable