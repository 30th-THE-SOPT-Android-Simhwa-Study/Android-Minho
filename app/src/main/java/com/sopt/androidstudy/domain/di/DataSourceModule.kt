package com.sopt.androidstudy.domain.di

import android.content.Context
import androidx.room.Room
import com.sopt.androidstudy.data.datasources.FriendDataSources
import com.sopt.androidstudy.data.model.db.FriendDAO
import com.sopt.androidstudy.data.model.db.FriendDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FriendDatabase {
        return Room.databaseBuilder(context, FriendDatabase::class.java, "friend_data_database")
            .build()
    }

    @Provides
    fun provideDao(database: FriendDatabase):FriendDAO{
        return database.friendDAO()
    }
}