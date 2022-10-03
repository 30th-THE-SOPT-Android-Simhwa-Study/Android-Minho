package com.sopt.androidstudy.domain.entity

import java.time.OffsetDateTime

data class ChatEntity(val messageId:Int, val send:Boolean, val createdAt:OffsetDateTime, val content:String?)
