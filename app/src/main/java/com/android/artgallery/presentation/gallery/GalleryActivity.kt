package com.android.artgallery.presentation.gallery


import android.os.Bundle
import com.android.artgallery.R
import com.android.artgallery.data.source.Album
import com.android.artgallery.presentation.album.AlbumsFragment
import com.android.artgallery.presentation.detailphoto.PhotoDetailActivity
import com.android.artgallery.presentation.photo.PhotosFragment
import dagger.android.support.DaggerAppCompatActivity


class GalleryActivity : DaggerAppCompatActivity(), OnGalleryCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        if (savedInstanceState == null) {
            navigateToGalleryPage()
        }
    }

    private fun navigateToGalleryPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.gallery_container,
                AlbumsFragment.newInstance(),
                AlbumsFragment.FRAGMENT_NAME
            ).commit()
    }

    override fun navigateToAlbumPage(album: Album) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.gallery_container,
                PhotosFragment.newInstance(album.id),
                PhotosFragment.FRAGMENT_NAME
            )
            .addToBackStack(PhotosFragment.FRAGMENT_NAME)
            .commit()
    }


    override fun gotoDetailPageByPhotoId(id: Long) {
        PhotoDetailActivity.start(this, id)
    }
}
