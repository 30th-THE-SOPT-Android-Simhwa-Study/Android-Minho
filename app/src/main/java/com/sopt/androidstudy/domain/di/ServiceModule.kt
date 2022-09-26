package com.sopt.androidstudy.domain.di

import com.sopt.androidstudy.data.remote.chatting.ChattingService
import com.sopt.androidstudy.data.remote.github.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {


    @Provides
    @Singleton
    fun provideChattingListRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://13.125.232.150:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGithubService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)

    @Provides
    @Singleton
    fun provideChattingService(retrofit: Retrofit): ChattingService =
        retrofit.create(ChattingService::class.java)
}