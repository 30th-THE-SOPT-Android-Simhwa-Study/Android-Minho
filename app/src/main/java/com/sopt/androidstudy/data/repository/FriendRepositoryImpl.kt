package com.sopt.androidstudy.data.repository

import androidx.lifecycle.LiveData
import com.sopt.androidstudy.data.datasources.FriendDataSources
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.types.MBTI
import com.sopt.androidstudy.data.model.types.MBTIFeatures
import com.sopt.androidstudy.domain.repository.FriendRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendRepositoryImpl @Inject constructor(private val friendDataSources: FriendDataSources) : FriendRepository {

    override suspend fun insert(friend: Friend): Boolean = friendDataSources.insert(friend)


    override suspend fun update(friend: Friend) {
        friendDataSources.update(friend)
    }

    override suspend fun delete(friend: Friend) {
        friendDataSources.delete(friend)
    }

    override suspend fun deleteAll() {
        friendDataSources.deleteAll()
    }

    override fun getMBTIFeatures(mbti: MBTI): List<MBTIFeatures>? =
        friendDataSources.getMBTIFeatures(mbti)


    override fun getAllFriends(): LiveData<List<Friend>> = friendDataSources.getAllFriends()

}