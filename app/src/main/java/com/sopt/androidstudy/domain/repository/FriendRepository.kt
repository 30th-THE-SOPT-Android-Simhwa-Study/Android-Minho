package com.sopt.androidstudy.domain.repository

import androidx.lifecycle.LiveData
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.types.MBTI
import com.sopt.androidstudy.data.model.types.MBTIFeatures
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    suspend fun insert(friend: Friend): Boolean

    suspend fun update(friend: Friend)

    suspend fun delete(friend: Friend)

    suspend fun deleteAll()

    fun getAllFriends(): LiveData<List<Friend>>

    fun getMBTIFeatures(mbti: MBTI): List<MBTIFeatures>?
}