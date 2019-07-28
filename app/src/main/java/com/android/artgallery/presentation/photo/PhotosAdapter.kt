package com.android.artgallery.presentation.photo

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.artgallery.R
import com.android.artgallery.databinding.HolderPhotoBinding
import com.android.artgallery.domain.model.Photo
import com.android.artgallery.presentation.loadImage
import com.android.artgallery.presentation.photo.PhotosAdapter.PhotoViewHolder
import java.util.*


/**
 * [android.support.v7.widget.RecyclerView.Adapter] to adapt
 * [Photo] items into [RecyclerView] via [PhotoViewHolder]
 *
 *
 * Created by ZARA on 02/02/2019.
 */
internal class PhotosAdapter(val mListener: OnPhotosAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = PhotosAdapter::class.java.name
    private val photos: MutableList<Photo> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPhotoBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_photo, parent, false
        )
        return PhotoViewHolder(holderPhotoBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Photo {
        return photos[position]
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun addData(list: List<Photo>) {
        this.photos.clear()
        this.photos.addAll(list)
        notifyDataSetChanged()
    }


    inner class PhotoViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {


        fun onBind(photo: Photo) {
            val holderPhotoBinding = dataBinding as HolderPhotoBinding
            holderPhotoBinding.photoViewModel = PhotoViewModel(photo)
            holderPhotoBinding.photoProgressBar.visibility = View.VISIBLE
            holderPhotoBinding.photoImageView.loadImage(photo.url, holderPhotoBinding.photoProgressBar)


            itemView.setOnClickListener {
                mListener.gotoDetailPage(holderPhotoBinding.photoImageView, photo.id)
            }

        }
    }
}
