package com.niketchoudhary.`in`.androidcodingchallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDao
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDataTable


@Database(
    entities = [
        ShadiMatchDataTable::class
    ], version = 1, exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class ShadiDatabase : RoomDatabase() {
    abstract fun shadiMatchDao(): ShadiMatchDao
}

