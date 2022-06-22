package com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEventMaterial

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    val id: Int,
    val name: String,
    val url: String
):Parcelable