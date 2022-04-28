package com.sopt.androidstudy.data.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

<<<<<<< HEAD
@Parcelize
data class UserData(val uid: String?, val password:String?):Parcelable
=======
data class UserData(val uid: String?, val password: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(password)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }
        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}
>>>>>>> 3dd44b49d75ed72054fa02c20bd04208f0a404a3
