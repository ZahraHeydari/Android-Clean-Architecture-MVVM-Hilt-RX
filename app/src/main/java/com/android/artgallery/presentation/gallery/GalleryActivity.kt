package com.android.artgallery.presentation.gallery


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.widget.ImageView
import com.android.artgallery.R
import com.android.artgallery.domain.model.Album
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
            ).commitAllowingStateLoss()
    }

    override fun navigateToAlbumPage(album: Album) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.gallery_container,
                PhotosFragment.newInstance(album.id),
                PhotosFragment.FRAGMENT_NAME
            )
            .addToBackStack(PhotosFragment.FRAGMENT_NAME)
            .commitAllowingStateLoss()
    }


    override fun gotoDetailPageByPhotoId(imageView: ImageView, id: Long) {

        val intent = Intent(this, PhotoDetailActivity::class.java)
        val bundle = Bundle().apply {
            putLong(KEY_PHOTO_ID, id)
        }
        intent.putExtras(bundle)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imageView,
            ViewCompat.getTransitionName(imageView) ?: ""
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }


    companion object {
        private val KEY_PHOTO_ID = "KEY_PHOTO_ID"
    }
}
