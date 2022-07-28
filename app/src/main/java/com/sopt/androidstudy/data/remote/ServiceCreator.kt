package com.sopt.androidstudy.data.remote
import com.sopt.androidstudy.data.remote.github.GithubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private val retrofitGithub: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val githubService: GithubService = retrofitGithub.create(GithubService::class.java)
}
