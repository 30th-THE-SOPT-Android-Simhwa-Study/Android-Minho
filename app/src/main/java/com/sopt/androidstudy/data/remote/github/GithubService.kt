package com.sopt.androidstudy.data.remote.github
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
        @Path("username") username: String
    ): Response<List<ResponseFollowing>>

    @GET("users/{username}/repos")
    suspend fun getRepository(
        @Path("username") username:String
    ): Response<List<ResponseRepo>>

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username:String
    ): Response<ResponseUser>

    @GET("/users/{username}/received_events")
    suspend fun getReceiveEvents(
        @Path("username") username:String
    ): Response<List<ResponseReceiveEvent>>
}