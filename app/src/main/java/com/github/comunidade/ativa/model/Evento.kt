package com.github.comunidade.ativa.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Evento() : BaseModel(), Parcelable {
    var date: Long? = null
    var description: String? = null
    var image: String? = null
    var longitude: Long? = null
    var latitude: Long? = null
    var price: Double? = null
    var title: String? = null
    var id: String? = null
    var cupons: Array<String>? = null
    var people: Array<People>? = null

    constructor(parcel: Parcel) : this() {
        date = parcel.readValue(Long::class.java.classLoader) as? Long
        description = parcel.readString()
        image = parcel.readString()
        longitude = parcel.readValue(Long::class.java.classLoader) as? Long
        latitude = parcel.readValue(Long::class.java.classLoader) as? Long
        price = parcel.readValue(Double::class.java.classLoader) as? Double
        title = parcel.readString()
        id = parcel.readString()
        cupons = parcel.createStringArray()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(date)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeValue(longitude)
        parcel.writeValue(latitude)
        parcel.writeValue(price)
        parcel.writeString(title)
        parcel.writeString(id)
        parcel.writeStringArray(cupons)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Evento> {
        override fun createFromParcel(parcel: Parcel): Evento {
            return Evento(parcel)
        }

        override fun newArray(size: Int): Array<Evento?> {
            return arrayOfNulls(size)
        }
    }
}