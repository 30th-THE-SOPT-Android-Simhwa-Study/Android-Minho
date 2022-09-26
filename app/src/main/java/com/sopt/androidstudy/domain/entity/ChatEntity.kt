package com.sopt.androidstudy.domain.entity

import java.time.LocalDate

data class ChatEntity(val messageId:Int, val send:Boolean, val createAt:LocalDate, val content:String)
