package com.android.data.di

import android.app.Application
import androidx.room.Room
import com.android.data.source.local.AppDatabase
import com.android.data.source.local.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): com.android.data.source.local.AppDatabase {
        return Room.databaseBuilder(
            application,
            com.android.data.source.local.AppDatabase::class.java,
            com.android.data.source.local.AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    internal fun providePhotoDao(appDatabase: com.android.data.source.local.AppDatabase): com.android.data.source.local.dao.PhotoDao {
        return appDatabase.photoDao
    }
}
