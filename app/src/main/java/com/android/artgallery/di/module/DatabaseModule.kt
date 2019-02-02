package com.android.artgallery.di.module

import dagger.Provides
import android.arch.persistence.room.Room
import android.app.Application
import com.android.artgallery.data.source.local.AppDatabase
import com.android.artgallery.data.source.local.dao.PhotoDao
import dagger.Module
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }


    @Provides
    internal fun providePhotoDao(appDatabase: AppDatabase): PhotoDao {
        return appDatabase.photoDao
    }
}