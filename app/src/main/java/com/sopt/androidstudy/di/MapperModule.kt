package com.sopt.androidstudy.di

import com.sopt.androidstudy.data.mappers.ChattingMapper
import com.sopt.androidstudy.presentation.mapper.chatting.ChattingListASCMapper
import com.sopt.androidstudy.presentation.mapper.chatting.MessageToChatMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideChattingMapper():ChattingMapper = ChattingMapper()

    @Provides
    @Singleton
    fun provideChattingUIMapper():ChattingListASCMapper = ChattingListASCMapper()


    @Provides
    @Singleton
    fun provideMessageToChatMapper():MessageToChatMapper = MessageToChatMapper()
}