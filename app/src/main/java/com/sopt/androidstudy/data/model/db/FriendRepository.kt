package com.sopt.androidstudy.data.model.db

import android.util.Patterns

class FriendRepository(private val dao: FriendDAO) {

    val friends = dao.getAllFriends()

    suspend fun insert(friend: Friend): Boolean {
        return if (Patterns.EMAIL_ADDRESS.matcher(friend.email).matches()) {
            dao.insertFriend(friend)
            true
        } else false
    }

    suspend fun update(friend: Friend) {
        dao.updateFriend(friend)
    }

    suspend fun delete(friend: Friend) {
        dao.deleteFriend(friend)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}