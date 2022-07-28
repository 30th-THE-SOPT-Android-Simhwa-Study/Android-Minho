package com.sopt.androidstudy.data.remote.github

import androidx.annotation.NonNull
import com.sopt.androidstudy.data.remote.github.models.ResponseFollowing
import com.sopt.androidstudy.data.remote.github.models.ResponseRepo
import com.sopt.androidstudy.data.remote.github.models.ResponseReceiveEvent
import com.sopt.androidstudy.data.remote.github.models.ResponseUser
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*

interface GithubService {

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @NonNull @Header("Authorization") token: String,
        @Path("username") username: String
    ): Response<List<ResponseFollowing>>

    @GET("users/{username}/repos")
    suspend fun getRepository(
        @NonNull @Header("Authorization") token: String,
        @Path("username") username: String
    ): Response<List<ResponseRepo>>

    @GET("users/{username}")
    suspend fun getUser(
        @NonNull @Header("Authorization") token: String,
        @Path("username") username: String
    ): Response<ResponseUser>

    @GET("/users/{username}/received_events")
    suspend fun getReceiveEvents(
        @NonNull @Header("Authorization") token: String,
        @Path("username") username: String
    ): Response<List<ResponseReceiveEvent>>
}