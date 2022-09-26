package com.sopt.androidstudy.data.remote.chatting

import com.sopt.androidstudy.data.model.chatting.Messages
import com.sopt.androidstudy.data.util.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ChattingService {
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE0OSIsImVtYWlsIjoibHNoMzI4MzI4QG5hdmVyLmNvbSIsImlhdCI6MTY2MzI0NzUyOCwiZXhwIjoxNjY0NDU3MTI4LCJpc3MiOiJwbGF5dG9nZXRoZXIifQ.M62jThpT65_8tSTW35x8hWrYaT56PJ7rOONSsbwqAK0")
    @GET("/api/message/{roomId}")
    suspend fun getChattingList(
        @Path("roomId") roomId: String,
    ): BaseResponse<Messages>
}