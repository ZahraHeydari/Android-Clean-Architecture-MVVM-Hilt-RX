package com.android.artgallery.presentation.detailphoto

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.artgallery.R
import com.android.artgallery.databinding.ActivityPhotoDetailBinding
import com.android.artgallery.presentation.loadImageFull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailActivity : AppCompatActivity() {

    private lateinit var activityPhotoDetailBinding: ActivityPhotoDetailBinding
    private val viewModel: PhotoDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPhotoDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_photo_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activityPhotoDetailBinding.photoDetailViewModel = viewModel

        val photoId = intent?.extras?.getLong(KEY_PHOTO_ID) ?: return

        with(viewModel) {
            getDetail(photoId)
            checkFavoriteStatus(photoId)

            photoData.observe(
                this@PhotoDetailActivity,
                Observer {
                    activityPhotoDetailBinding.detailTitleTextView.text = it?.title
                    activityPhotoDetailBinding.detailToolbarImageView.loadImageFull(it?.url)
                }
            )

            isFavorite.observe(
                this@PhotoDetailActivity,
                Observer {
                    it?.let {
                        activityPhotoDetailBinding.detailFab.setImageResource(
                            if (it) R.drawable.ic_star_full_vector else R.drawable.ic_star_empty_white_vector
                        )
                    }
                }
            )

            activityPhotoDetailBinding.detailFab.setOnClickListener {
                updateFavoriteStatus()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val KEY_PHOTO_ID = "KEY_PHOTO_ID"
    }
}
