package com.sopt.androidstudy.data.remote.github.models

import android.os.Parcelable
import com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEventMaterial.Actor
import com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEventMaterial.Org
import com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEventMaterial.Payload
import kotlinx.parcelize.Parcelize

data class ResponseReceiveEvent(
    val id: String,
    val type: String,
    val actor: Actor,
    val payload: Payload,
    val org: Org
)