package com.example.serializationtest.parcelable_model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Sergey Panshyn on 30.01.2018.
 */

class MyObjectParcelable(var age: Int, var name: String, var list: List<DataParcelable>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.createTypedArrayList(DataParcelable)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(age)
        parcel.writeString(name)
        parcel.writeTypedList(list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyObjectParcelable> {
        override fun createFromParcel(parcel: Parcel): MyObjectParcelable {
            return MyObjectParcelable(parcel)
        }

        override fun newArray(size: Int): Array<MyObjectParcelable?> {
            return arrayOfNulls(size)
        }
    }


}
