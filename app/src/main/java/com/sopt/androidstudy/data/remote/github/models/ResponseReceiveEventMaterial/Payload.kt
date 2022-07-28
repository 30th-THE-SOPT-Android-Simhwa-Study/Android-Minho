package com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEventMaterial

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payload(
    val size: Int,
    val commits: List<Commit>,
    val ref: String,
):Parcelable