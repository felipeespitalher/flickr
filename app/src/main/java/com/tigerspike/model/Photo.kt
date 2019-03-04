package com.tigerspike.model

import android.os.Parcel
import android.os.Parcelable

data class Photo(
        val farm: String,
        val server: String,
        val id: String,
        val secret: String,
        val title: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(farm)
        parcel.writeString(server)
        parcel.writeString(id)
        parcel.writeString(secret)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}