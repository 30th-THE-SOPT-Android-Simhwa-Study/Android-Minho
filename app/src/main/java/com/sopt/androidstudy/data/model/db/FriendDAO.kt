package com.sopt.androidstudy.data.model.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FriendDAO {
    @Insert
    suspend fun insertFriend(friend: Friend) : Long

    @Update
    suspend fun updateFriend(friend: Friend)

    @Delete
    suspend fun deleteFriend(friend: Friend)

    @Query("DELETE FROM friend_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM friend_data_table")
    fun getAllFriends(): LiveData<List<Friend>>
}