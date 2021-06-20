package com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module

import android.app.Application
import androidx.room.Room
import com.niketchoudhary.`in`.androidcodingchallenge.database.PrefManager
import com.niketchoudhary.`in`.androidcodingchallenge.database.ShadiDatabase
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): ShadiDatabase {
        return Room.databaseBuilder(
            application,
            ShadiDatabase::class.java, "shadiDatabase.db"
        )
            .build()
    }

    @Provides
    fun providePrefManager(application: Application): PrefManager {
        return PrefManager.getInstance(application.applicationContext)
    }

    @Singleton
    @Provides
    fun provideShadiMatchDao(db: ShadiDatabase): ShadiMatchDao {
        return db.shadiMatchDao()
    }
}