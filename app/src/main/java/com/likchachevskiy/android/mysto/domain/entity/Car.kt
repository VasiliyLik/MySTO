package com.likchachevskiy.android.mysto.domain.entity

import android.os.Parcel
import android.os.Parcelable

data class Car(
    var id: Int,
    var carModel: String,
    var carProducer: String,
    var plateNumber: String,
    var photo: Int,
    var ownerName: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(carModel)
        parcel.writeString(carProducer)
        parcel.writeString(plateNumber)
        parcel.writeInt(photo)
        parcel.writeString(ownerName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }
}

