package com.sopt.androidstudy.domain.entity

import java.time.OffsetDateTime

data class ChatEntity(val messageId:Int, val send:Boolean, val createAt:OffsetDateTime, val content:String)
