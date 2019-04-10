package com.android.artgallery.data

import android.arch.persistence.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.android.artgallery.data.source.local.AppDatabase
import com.android.artgallery.domain.model.Photo
import com.android.artgallery.util.TestUtil
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class PhotoDaoTest {

    private lateinit var mDatabase: AppDatabase

    @Before
    fun createDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java)
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun isPhotoListEmpty(){
        assertEquals(0,mDatabase.photoDao.loadAll().size)
    }


    @Test
    @Throws(Exception::class)
    fun insertPhoto() {
        val photo: Photo = TestUtil.createPhoto(7)
        val insertedPhoto = mDatabase.photoDao.insert(photo)
        assertNotNull(insertedPhoto)
    }


    @Test
    @Throws(Exception::class)
    fun insertPhotoAndLoadByTitle() {
        val photo: Photo = TestUtil.createPhoto(1).apply {
            setName("Art")
        }
        mDatabase.photoDao.insert(photo)
        val photoLoadedByTitle = mDatabase.photoDao.loadOneByPhotoTitle("Art")
        assertThat(photoLoadedByTitle, equalTo(photo))
    }

    @Test
    @Throws(Exception::class)
    fun retrievesPhotos(){
        val photoList = TestUtil.makePhotoList(5)
        photoList.forEach {
            mDatabase.photoDao.insert(it)
        }

        val loadedPhotos = mDatabase.photoDao.loadAll()
        assertEquals(photoList,loadedPhotos)
    }


    @Test
    @Throws(Exception::class)
    fun deletePhoto(){
        val photo = TestUtil.createPhoto(8)
        mDatabase.photoDao.delete(photo)

        val loadOneByPhotoId = mDatabase.photoDao.loadOneByPhotoId(8)
        assertNull(loadOneByPhotoId)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllPhotos(){
        mDatabase.photoDao.deleteAll()
        val loadedAllPhotos = mDatabase.photoDao.loadAll()
        assert(loadedAllPhotos.isEmpty())
    }


}