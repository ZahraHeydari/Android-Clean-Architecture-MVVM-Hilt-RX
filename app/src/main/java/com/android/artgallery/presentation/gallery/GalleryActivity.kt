package com.android.artgallery.presentation.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.artgallery.R
import com.android.artgallery.databinding.ActivityGalleryBinding
import com.android.artgallery.presentation.album.AlbumsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.gallery_container,
                AlbumsFragment.newInstance(),
                AlbumsFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
        }
    }
}
