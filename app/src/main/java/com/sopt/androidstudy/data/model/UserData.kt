package com.sopt.androidstudy.data.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(val uid: String?, val password:String?):Parcelable
