package com.android.artgallery.presentation.photo

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.artgallery.R
import com.android.artgallery.data.Photo
import com.android.artgallery.databinding.HolderPhotoBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.ArrayList





/**
 * [android.support.v7.widget.RecyclerView.Adapter] to adapt
 * [Photo] items into [RecyclerView] via [PhotoViewHolder]
 *
 *
 * Created by ZARA on 02/02/2019.
 */
internal class PhotosAdapter(private val listener: OnPhotosAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val photos: MutableList<Photo>?
    private val mListener: OnPhotosAdapterListener

    init {
        this.photos = ArrayList()
        mListener = listener
    }

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
        return photos!![position]
    }

    override fun getItemCount(): Int {
        return photos?.size ?: 0
    }

    fun addData(list: List<Photo>) {
        this.photos!!.clear()
        this.photos.addAll(list)
        notifyDataSetChanged()
    }

    /**
     * Holder of [Photo]
     */
    inner class PhotoViewHolder(dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {

        private val dataBinding: ViewDataBinding

        init {
            this.dataBinding = dataBinding
        }

        fun onBind(photo: Photo) {
            val holderPhotoBinding = this.dataBinding as HolderPhotoBinding
            holderPhotoBinding.photoViewModel = PhotoViewModel(photo)
            holderPhotoBinding.photoProgressBar.visibility = View.VISIBLE
            try {
                Picasso.get()
                    .load(photo.url)
                    .placeholder(android.R.color.white)
                    .into(holderPhotoBinding.photoImageView, object : Callback{

                        override fun onError(e: java.lang.Exception?) {
                            e?.printStackTrace()

                        }

                        override fun onSuccess() {
                            holderPhotoBinding.photoProgressBar.visibility = View.GONE
                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }

            itemView.setOnClickListener {
                mListener.gotoDetailPage(photo.id)
            }

        }
    }

    companion object {

        private val TAG = PhotosAdapter::class.java.simpleName
    }
}
