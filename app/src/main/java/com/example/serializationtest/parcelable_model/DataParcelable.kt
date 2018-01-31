package com.example.serializationtest.parcelable_model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Sergey Panshyn on 30.01.2018.
 */
class DataParcelable(var i: Int, var s: String, var b: Boolean, var l: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(i)
        parcel.writeString(s)
        parcel.writeByte(if (b) 1 else 0)
        parcel.writeLong(l)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataParcelable> {
        override fun createFromParcel(parcel: Parcel): DataParcelable {
            return DataParcelable(parcel)
        }

        override fun newArray(size: Int): Array<DataParcelable?> {
            return arrayOfNulls(size)
        }
    }
}