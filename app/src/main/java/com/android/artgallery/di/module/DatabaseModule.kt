package com.android.artgallery.di.module

import dagger.Provides
import androidx.room.Room
import android.app.Application
import com.android.artgallery.data.source.local.AppDatabase
import com.android.artgallery.data.source.local.dao.PhotoDao
import com.android.artgallery.di.component.ApplicationComponent
import dagger.Module
import dagger.hilt.InstallIn
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
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