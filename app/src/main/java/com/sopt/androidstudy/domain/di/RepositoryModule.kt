package com.sopt.androidstudy.domain.di

import com.sopt.androidstudy.data.repository.ChattingRepositoyImpl
import com.sopt.androidstudy.data.repository.FriendRepositoryImpl
import com.sopt.androidstudy.domain.repository.ChattingRepository
import com.sopt.androidstudy.domain.repository.FriendRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindToRepository(impl: FriendRepositoryImpl): FriendRepository

    @Binds
    @ViewModelScoped
    abstract fun bindToChattingRepository(chattingRepositoyImpl: ChattingRepositoyImpl):ChattingRepository
}