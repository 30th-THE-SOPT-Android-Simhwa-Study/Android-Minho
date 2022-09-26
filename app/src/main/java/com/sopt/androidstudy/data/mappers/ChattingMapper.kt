package com.sopt.androidstudy.data.mappers

import com.sopt.androidstudy.data.model.chatting.ChatDto
import com.sopt.androidstudy.data.util.BaseMapper
import com.sopt.androidstudy.domain.entity.ChatEntity
import java.time.OffsetDateTime

@Suppress("UNCHECKED_CAST")
class ChattingMapper : BaseMapper<List<ChatDto>, List<ChatEntity>> {
    override fun map(from: List<ChatDto>): List<ChatEntity> =
        (from.map {
            OffsetDateTime.parse(it.createAt)
        } as List<ChatEntity>)
}