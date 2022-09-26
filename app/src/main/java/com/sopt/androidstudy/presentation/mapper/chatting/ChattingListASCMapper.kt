package com.sopt.androidstudy.presentation.mapper.chatting

import com.sopt.androidstudy.data.util.BaseMapper
import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.presentation.model.chating.Chat
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Suppress("UNCHECKED_CAST")
class ChattingListASCMapper : BaseMapper<List<ChatEntity>, List<Chat>> {
    override fun map(from: List<ChatEntity>): List<Chat> =
        (from.sortedBy { it.messageId }.map {
            (if (it.createAt.hour / 12 == 0) "오전 " else "오후 ") + it.createAt.truncatedTo(ChronoUnit.SECONDS)
                .format(
                    DateTimeFormatter.ISO_TIME
                ).substring(0, 5)
        } as List<Chat>)


}