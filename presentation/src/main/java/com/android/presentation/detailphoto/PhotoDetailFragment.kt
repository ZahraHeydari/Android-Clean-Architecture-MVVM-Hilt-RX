package com.android.presentation.detailphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.artgallery.presentation.loadImage
import com.android.presentation.R
import com.android.presentation.databinding.FragmentPhotoDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment : Fragment() {

    private lateinit var binding: FragmentPhotoDetailBinding
    private val viewModel: PhotoDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoId = arguments?.getLong(KEY_PHOTO_ID) ?: return
        with(viewModel) {
            getDetail(photoId)
            checkFavoriteStatus(photoId)

            photoData.observe(viewLifecycleOwner) {
                binding.detailTitleTextView.text = it?.title
                binding.detailToolbarImageView.loadImage(it?.url)
            }

            isFavorite.observe(viewLifecycleOwner) {
                it?.let {
                    binding.detailFab.setImageResource(
                        if (it) R.drawable.ic_star_full_vector else R.drawable.ic_star_empty_white_vector
                    )
                }
            }

            binding.detailFab.setOnClickListener {
                updateFavoriteStatus()
            }
        }
    }

    companion object {
        val FRAGMENT_NAME: String = PhotoDetailFragment::class.java.name
        private const val KEY_PHOTO_ID = "KEY_PHOTO_ID"

        fun newInstance(photoId: Long) = PhotoDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_PHOTO_ID, photoId)
            }
        }
    }
}