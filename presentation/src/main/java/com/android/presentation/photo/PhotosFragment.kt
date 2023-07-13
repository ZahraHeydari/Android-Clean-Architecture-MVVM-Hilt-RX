package com.android.presentation.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.presentation.R
import com.android.presentation.databinding.FragmentPhotosBinding
import com.android.presentation.detailphoto.PhotoDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private lateinit var binding: FragmentPhotosBinding
    private var adapter: PhotosAdapter? = null
    private val viewModel: PhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        adapter = PhotosAdapter { gotoDetailPageByPhotoId(it) }
        binding.photosRecyclerView.adapter = adapter
        viewModel.loadPhotos(arguments?.getLong(KEY_ALBUM_ID))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {

            isLoad.observe(viewLifecycleOwner) {
                it?.let { visibility ->
                    binding.photosProgressBar.visibility =
                        if (visibility) View.GONE else View.VISIBLE
                }
            }

            photoListReceivedLiveData.observe(viewLifecycleOwner) {
                it?.let {
                    adapter?.addData(it)
                }
            }
        }
    }

    private fun gotoDetailPageByPhotoId(id: Long) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.gallery_container,
            PhotoDetailFragment.newInstance(id),
            PhotoDetailFragment.FRAGMENT_NAME
        )?.addToBackStack(PhotoDetailFragment.FRAGMENT_NAME)?.commitAllowingStateLoss()
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
    }

    companion object {
        val FRAGMENT_NAME: String = PhotosFragment::class.java.name
        private const val KEY_ALBUM_ID = "KEY_ALBUM_ID"

        @JvmStatic
        fun newInstance(id: Long) = PhotosFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_ALBUM_ID, id)
            }
        }
    }
}
