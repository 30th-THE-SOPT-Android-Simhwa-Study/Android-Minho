package com.sopt.androidstudy.domain.di

import com.sopt.androidstudy.data.remote.chatting.ChattingService
import com.sopt.androidstudy.data.remote.github.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {


    private val loggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder().addHeader(
                "Authorization",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE0OSIsImVtYWlsIjoibHNoMzI4MzI4QG5hdmVyLmNvbSIsImlhdCI6MTY2MzI0NzUyOCwiZXhwIjoxNjY0NDU3MTI4LCJpc3MiOiJwbGF5dG9nZXRoZXIifQ.M62jThpT65_8tSTW35x8hWrYaT56PJ7rOONSsbwqAK0"
            ).addHeader("Content-Type", "application/json").build()
            return chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideUnAuthOkHttpClientBuilder(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor())
            .build()

    @Provides
    @Singleton
    fun provideChattingListRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://13.125.232.150:3000")
            .client(okHttpClient)
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