package com.sopt.androidstudy.presentation.mapper.chatting

import com.sopt.androidstudy.data.util.BaseMapper
import com.sopt.androidstudy.presentation.model.chating.Chat
import com.sopt.androidstudy.service.Message

class MessageToChatMapper:BaseMapper<Message,Chat> {
    override fun map(from: Message): Chat = Chat(from.audienceId, false, from.createdAt, from.content)
}