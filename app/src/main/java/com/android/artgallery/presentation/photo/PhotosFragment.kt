package com.android.artgallery.presentation.photo

import androidx.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.artgallery.R
import com.android.artgallery.databinding.FragmentPhotosBinding
import com.android.artgallery.presentation.gallery.OnGalleryCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment(), OnPhotosAdapterListener {

    private lateinit var fragmentPhotosBinding: FragmentPhotosBinding
    private var adapter: PhotosAdapter? = null
    private var mCallback: OnGalleryCallback? = null
    private val viewModel: PhotosViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnGalleryCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnGalleryCallback!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PhotosAdapter(this)
        val albumId = arguments?.let { it.getLong(KEY_ALBUM_ID) }
        viewModel.loadPhotos(albumId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        fragmentPhotosBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)
        fragmentPhotosBinding.photosViewModel = viewModel
        fragmentPhotosBinding.photosRecyclerView.adapter = adapter

        viewModel.isLoad.observe(viewLifecycleOwner, Observer {
            it?.let { visibility ->
                fragmentPhotosBinding.photosProgressBar.visibility =
                    if (visibility) View.GONE else View.VISIBLE
            }
        })

        viewModel.photoListReceivedLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter?.addData(it)
            }
        })

        return fragmentPhotosBinding.root
    }

    override fun gotoDetailPage(imageView: ImageView, id: Long) {
        mCallback?.gotoDetailPageByPhotoId(imageView, id)
    }

    override fun onDetach() {
        super.onDetach()
        mCallback = null
        adapter = null
    }

    companion object {

        val KEY_ALBUM_ID = "KEY_ALBUM_ID"
        val FRAGMENT_NAME = PhotosFragment::class.java.name

        @JvmStatic
        fun newInstance(id: Long) = PhotosFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_ALBUM_ID, id)
            }
        }
    }
}