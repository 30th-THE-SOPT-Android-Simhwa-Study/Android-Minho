package com.sopt.androidstudy.data.mappers

import com.sopt.androidstudy.data.model.chatting.ChatDto
import com.sopt.androidstudy.data.util.BaseMapper
import com.sopt.androidstudy.domain.entity.ChatEntity
import java.time.OffsetDateTime

class ChattingMapper : BaseMapper<List<ChatDto>, List<ChatEntity>> {
    override fun map(from: List<ChatDto>): List<ChatEntity> =
        from.map {
            ChatEntity(
                it.messageId,
                it.send,
                OffsetDateTime.parse(it.createdAt ?: "2022-04-26T11:01:28.003Z"),
                it.content
            )
        }
}