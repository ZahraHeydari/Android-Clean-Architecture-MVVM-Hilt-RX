package com.android.artgallery.presentation.detailphoto

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.android.artgallery.R
import com.android.artgallery.databinding.ActivityPhotoDetailBinding
import com.android.artgallery.presentation.loadImageFull
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PhotoDetailActivity : DaggerAppCompatActivity(), OnPhotoDetailCallback {

    private val TAG = PhotoDetailActivity::class.java.name
    private lateinit var activityPhotoDetailBinding: ActivityPhotoDetailBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PhotoDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PhotoDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityPhotoDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        activityPhotoDetailBinding.photoDetailViewModel = viewModel

        val photoId = intent?.extras?.getLong(KEY_PHOTO_ID) ?: return
        viewModel.getDetail(photoId)
        viewModel.checkFavoriteStatus(photoId)

        viewModel.photoData.observe(this, Observer {
            activityPhotoDetailBinding.detailTitleTextView.text = it?.title
            activityPhotoDetailBinding.detailToolbarImageView.loadImageFull(it?.url)
        })


        viewModel.isFavorite.observe(this, Observer {
            it?.let {
                activityPhotoDetailBinding.detailFab.setImageResource(if (it) R.drawable.ic_star_full_vector else R.drawable.ic_star_empty_white_vector)
            }
        })

        activityPhotoDetailBinding.detailFab.setOnClickListener {
            viewModel.updateFavoriteStatus()
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