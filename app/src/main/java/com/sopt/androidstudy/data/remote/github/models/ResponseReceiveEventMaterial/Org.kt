package com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEventMaterial

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Org(
    val avatar_url: String,
    val login: String,
):Parcelable