package com.sopt.androidstudy.data.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Friend::class], version = 1)
abstract class FriendDatabase : RoomDatabase() {

    abstract fun friendDAO(): FriendDAO

}