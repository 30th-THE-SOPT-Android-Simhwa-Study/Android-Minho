package com.sopt.androidstudy.data.datasources

import android.util.Patterns
import androidx.lifecycle.LiveData
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.data.model.db.FriendDAO
import com.sopt.androidstudy.data.model.types.MBTI
import com.sopt.androidstudy.data.model.types.MBTIFeatures

class FriendDataSources(private val dao: FriendDAO) {

    suspend fun insert(friend: Friend): Boolean =
        if (Patterns.EMAIL_ADDRESS.matcher(friend.email).matches()) {
            dao.insertFriend(friend)
            true
        } else false

    suspend fun update(friend: Friend) {
        dao.updateFriend(friend)
    }

    suspend fun delete(friend: Friend) {
        dao.deleteFriend(friend)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    fun getAllFriends(): LiveData<List<Friend>> = dao.getAllFriends()

    fun getMBTIFeatures(mbti: MBTI): List<MBTIFeatures>? {

        return when (mbti) {
            MBTI.ENFJ -> listOf<MBTIFeatures>(
                MBTIFeatures.ENFJ1,
                MBTIFeatures.ENFJ2,
                MBTIFeatures.ENFJ3
            )
            MBTI.ENFP -> listOf<MBTIFeatures>(
                MBTIFeatures.ENFP1,
                MBTIFeatures.ENFP2,
                MBTIFeatures.ENFP3
            )
            MBTI.ENTJ -> listOf<MBTIFeatures>(
                MBTIFeatures.ENTJ1,
                MBTIFeatures.ENTJ2,
                MBTIFeatures.ENTJ3
            )
            MBTI.ENTP -> listOf<MBTIFeatures>(
                MBTIFeatures.ENTP1,
                MBTIFeatures.ENTP2,
                MBTIFeatures.ENTP3
            )
            MBTI.ENFJ -> listOf<MBTIFeatures>(
                MBTIFeatures.ENFJ1,
                MBTIFeatures.ENFJ2,
                MBTIFeatures.ENFJ3
            )
            MBTI.ESFP -> listOf<MBTIFeatures>(
                MBTIFeatures.ESFP1,
                MBTIFeatures.ESFP2,
                MBTIFeatures.ESFP3
            )
            MBTI.ESTJ -> listOf<MBTIFeatures>(
                MBTIFeatures.ESTJ1,
                MBTIFeatures.ESTJ2,
                MBTIFeatures.ESTJ3
            )
            MBTI.ESTP -> listOf<MBTIFeatures>(
                MBTIFeatures.ESTP1,
                MBTIFeatures.ESTP2,
                MBTIFeatures.ESTP3
            )

            MBTI.ENFJ -> listOf<MBTIFeatures>(
                MBTIFeatures.ENFJ1,
                MBTIFeatures.ENFJ2,
                MBTIFeatures.ENFJ3
            )
            MBTI.ESFP -> listOf<MBTIFeatures>(
                MBTIFeatures.ESFP1,
                MBTIFeatures.ESFP2,
                MBTIFeatures.ESFP3
            )
            MBTI.ESTJ -> listOf<MBTIFeatures>(
                MBTIFeatures.ESTJ1,
                MBTIFeatures.ESTJ2,
                MBTIFeatures.ESTJ3
            )
            MBTI.ESTP -> listOf<MBTIFeatures>(
                MBTIFeatures.ESTP1,
                MBTIFeatures.ESTP2,
                MBTIFeatures.ESTP3
            )

            MBTI.ISFJ -> listOf<MBTIFeatures>(
                MBTIFeatures.ISFJ1,
                MBTIFeatures.ISFJ2,
                MBTIFeatures.ISFJ3
            )
            MBTI.ISFP -> listOf<MBTIFeatures>(
                MBTIFeatures.ISFP1,
                MBTIFeatures.ISFP2,
                MBTIFeatures.ISFP3
            )
            MBTI.ISTJ -> listOf<MBTIFeatures>(
                MBTIFeatures.ISTJ1,
                MBTIFeatures.ISTJ2,
                MBTIFeatures.ISTJ3
            )
            MBTI.ISTP -> listOf<MBTIFeatures>(
                MBTIFeatures.ISTP1,
                MBTIFeatures.ISTP2,
                MBTIFeatures.ISTP3
            )
            else -> null

        }
    }
}
