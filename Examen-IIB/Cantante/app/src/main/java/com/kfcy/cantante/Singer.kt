package com.kfcy.cantante

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.Exclude

data class Singer(
    @Exclude @JvmField var id: String?,
    val name: String,
    val lastn: String,
    @Exclude @JvmField var songs: List<Song> = emptyList()
) : Parcelable {
    // Empty constructor used by Firebase
    constructor() : this(null, "", "", emptyList())

    // Object constructor
    constructor(name: String, lastn: String) : this(null, name, lastn, emptyList())

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(Song)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(lastn)
        parcel.writeTypedList(songs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Singer> {
        override fun createFromParcel(parcel: Parcel): Singer {
            return Singer(parcel)
        }

        override fun newArray(size: Int): Array<Singer?> {
            return arrayOfNulls(size)
        }
    }
}