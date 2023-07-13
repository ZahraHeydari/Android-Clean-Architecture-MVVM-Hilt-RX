package com.android.presentation.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.presentation.R
import com.android.presentation.album.AlbumsFragment
import com.android.presentation.databinding.ActivityGalleryBinding
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
                R.id.gallery_container, AlbumsFragment.newInstance(), AlbumsFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
        }
    }
}
