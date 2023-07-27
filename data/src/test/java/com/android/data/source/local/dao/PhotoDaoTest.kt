package com.android.data.source.local.dao

import com.android.data.TestUtil
import com.android.data.source.local.entity.PhotoEntity
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PhotoDaoTest {

    private var photoDao = mockk<PhotoDao>()

    @Test
    fun isPhotoListEmpty() {
        every { photoDao.loadAll() } returns mutableListOf()
        Assert.assertEquals(0, photoDao.loadAll().size)
    }

    @Test
    fun insertPhoto() {
        val photoEntity: PhotoEntity = TestUtil.createPhoto(7)

        every { photoDao.insert(photoEntity) } returns 1L
        val insertedPhoto = photoDao.insert(photoEntity)

        Assert.assertNotNull(insertedPhoto)
    }

    @Test
    fun insertPhotoAndLoadByTitle() {
        val photoTitle = "Art"
        val photoEntity: PhotoEntity = TestUtil.createPhoto(1).apply {
            title = photoTitle
        }
        every { photoDao.insert(photoEntity) } returns 1L

        every { photoDao.loadOneByPhotoTitle(photoTitle) } returns photoEntity
        val photoLoadedByTitle = photoDao.loadOneByPhotoTitle(photoTitle)

        MatcherAssert.assertThat(photoLoadedByTitle, CoreMatchers.equalTo(photoEntity))
    }

    @Test
    fun retrievesPhotos() {
        val photoEntities = TestUtil.makePhotoList(5)
        photoEntities.forEach { photoDao.insert(it) }

        every { photoDao.loadAll() } returns mutableListOf()
        val loadedPhotos = photoDao.loadAll()

        Assert.assertEquals(photoEntities, loadedPhotos)
    }

    @Test
    fun deletePhoto() {
        val photoId = 8L
        val photoEntity = TestUtil.createPhoto(photoId)

        every { photoDao.delete(photoEntity) } returns Unit
        photoDao.delete(photoEntity)

        every { photoDao.loadOneByPhotoId(photoId) } returns null
        val loadOneByPhotoId = photoDao.loadOneByPhotoId(photoId)

        Assert.assertNull(loadOneByPhotoId)
    }

    @Test
    fun deleteAllPhotos() {
        every { photoDao.deleteAll() } returns Unit
        photoDao.deleteAll()

        every { photoDao.loadAll() } returns mutableListOf()
        val loadedAllPhotos = photoDao.loadAll()

        assert(loadedAllPhotos.isEmpty())
    }
}