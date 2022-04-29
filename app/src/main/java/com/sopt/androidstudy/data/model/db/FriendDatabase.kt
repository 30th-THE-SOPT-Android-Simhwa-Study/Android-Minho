package com.sopt.androidstudy.data.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Friend::class], version = 1)
abstract class FriendDatabase : RoomDatabase() {

    abstract val friendDAO: FriendDAO

    companion object {
        @Volatile
        private var INSTANCE: FriendDatabase? = null
        fun getInstance(context: Context): FriendDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FriendDatabase::class.java,
                        "friend_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}