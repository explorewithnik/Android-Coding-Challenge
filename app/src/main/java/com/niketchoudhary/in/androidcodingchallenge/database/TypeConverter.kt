package com.niketchoudhary.`in`.androidcodingchallenge.database

import com.google.gson.Gson
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDataTable

class TypeConverter {

    @androidx.room.TypeConverter
    fun objectToName(value: ShadiMatchDataTable.Name?): String? {
        return Gson().toJson(value)
    }

    @androidx.room.TypeConverter
    fun jsonToObjectName(value: String?): ShadiMatchDataTable.Name? {
        return Gson().fromJson(value, ShadiMatchDataTable.Name::class.java)
    }

    @androidx.room.TypeConverter
    fun objectToLocation(value: ShadiMatchDataTable.Location?): String? {
        return Gson().toJson(value)
    }

    @androidx.room.TypeConverter
    fun jsonToObjectLocation(value: String?): ShadiMatchDataTable.Location? {
        return Gson().fromJson(value, ShadiMatchDataTable.Location::class.java)
    }


    @androidx.room.TypeConverter
    fun objectToPicture(value: ShadiMatchDataTable.Picture?): String? {
        return Gson().toJson(value)
    }

    @androidx.room.TypeConverter
    fun jsonToObjectPicture(value: String?): ShadiMatchDataTable.Picture? {
        return Gson().fromJson(value, ShadiMatchDataTable.Picture::class.java)
    }


    @androidx.room.TypeConverter
    fun objectToDob(value: ShadiMatchDataTable.CustomAge?): String? {
        return Gson().toJson(value)
    }

    @androidx.room.TypeConverter
    fun jsonToObjectDob(value: String?): ShadiMatchDataTable.CustomAge? {
        return Gson().fromJson(value, ShadiMatchDataTable.CustomAge::class.java)
    }

    @androidx.room.TypeConverter
    fun listToJson(value: MutableList<String?>?): String? {
        return Gson().toJson(value)
    }

    @androidx.room.TypeConverter
    fun jsonToList(value: String?): MutableList<String?>? {
        val objects = Gson().fromJson(value, Array<String?>::class.java)
        return objects?.toMutableList()
    }

    @androidx.room.TypeConverter
    fun intToJson(value: List<Int>): String? {
        return Gson().toJson(value)
    }

    @androidx.room.TypeConverter
    fun jsonToInt(value: String): List<Int> {
        val objects = Gson().fromJson(value, Array<Int>::class.java)
        return objects.toList()
    }

    @androidx.room.TypeConverter
    fun longToJson(value: List<Long>): String? {
        return Gson().toJson(value)
    }

    @androidx.room.TypeConverter
    fun jsonToLong(value: String): List<Long> {
        val objects = Gson().fromJson(value, Array<Long>::class.java)
        return objects.toList()
    }


}