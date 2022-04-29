package com.sopt.androidstudy.data.model.db

class FriendRepository(private val dao: FriendDAO) {

    val friends = dao.getAllFriends()

    suspend fun insert(friend: Friend){
        dao.insertFriend(friend)
    }

    suspend fun update(friend: Friend){
        dao.updateFriend(friend)
    }

    suspend fun delete(friend: Friend){
        dao.deleteFriend(friend)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}