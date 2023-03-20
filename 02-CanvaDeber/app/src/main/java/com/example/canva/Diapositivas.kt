package com.example.canva

import android.os.Parcel
import android.os.Parcelable

class Diapositivas (
    var id: Int,
    var nombre: String?,
    var descripcion: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }

    companion object CREATOR : Parcelable.Creator<Diapositivas> {
        override fun createFromParcel(parcel: Parcel): Diapositivas {
            return Diapositivas(parcel)
        }

        override fun newArray(size: Int): Array<Diapositivas?> {
            return arrayOfNulls(size)
        }
    }
}
