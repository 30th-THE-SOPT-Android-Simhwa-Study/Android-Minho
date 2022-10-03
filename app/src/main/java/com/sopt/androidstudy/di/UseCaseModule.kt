package com.sopt.androidstudy.di

import com.sopt.androidstudy.domain.usecase.WhenEnterChattingRoom
import com.sopt.androidstudy.domain.usecase.WhenEnterChattingRoomImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindChattingRoomUseCase(whenEnterChattingRoomImpl: WhenEnterChattingRoomImpl):WhenEnterChattingRoom
}