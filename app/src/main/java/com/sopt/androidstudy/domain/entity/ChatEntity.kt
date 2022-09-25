package com.sopt.androidstudy.domain.entity

import java.util.Date

data class ChatEntity(val messageId:Int, val send:Boolean, val createAt:Date, val content:String)
