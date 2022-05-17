package com.sopt.androidstudy.domain.di

import com.sopt.androidstudy.data.repository.FriendRepositoryImpl
import com.sopt.androidstudy.domain.repository.FriendRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindToRepository(impl: FriendRepositoryImpl): FriendRepository


}