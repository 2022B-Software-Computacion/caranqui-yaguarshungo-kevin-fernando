package com.kfcy.cantante

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.Exclude

data class Song(
    @Exclude @JvmField var id: String?,
    val name: String = "",
    val album: String = "",
    val releaseDate: String = ""
) : Parcelable {
    // Empty constructor used by Firebase
    constructor() : this(null, "", "","")

    // Object constructor
    constructor(name: String, album: String, releaseDate: String) : this(null, name, album, releaseDate)

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(album)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }

}
