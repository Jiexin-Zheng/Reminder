package com.example.reminder

import android.os.Parcel
import android.os.Parcelable


class BeanOfReminder(var id: Long, var reminder_seen: Boolean = false, var reminder_time: String?, var reminder_message: String?,
                     var creation_time: String?, var location_x: Long, var location_y: Long, var icon_id: Long) :
        Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString(),
                    parcel.readString(),
            parcel.readLong(),
            parcel.readLong(),
            parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeByte(if (reminder_seen) 1 else 0)
        parcel.writeString(reminder_time)
        parcel.writeString(reminder_message)
        parcel.writeString(creation_time)
        parcel.writeLong(location_x)
        parcel.writeLong(location_y)
        parcel.writeLong(icon_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BeanOfReminder> {
        override fun createFromParcel(parcel: Parcel): BeanOfReminder {
            return BeanOfReminder(parcel)
        }

        override fun newArray(size: Int): Array<BeanOfReminder?> {
            return arrayOfNulls(size)
        }
    }


}