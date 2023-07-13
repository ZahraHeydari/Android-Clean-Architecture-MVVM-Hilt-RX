package com.android.presentation.photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.model.Photo
import com.android.artgallery.presentation.loadImage
import com.android.presentation.databinding.HolderPhotoBinding
import com.android.presentation.photo.PhotosAdapter.PhotoViewHolder

/**
 * [android.support.v7.widget.RecyclerView.Adapter] to adapt
 * [Photo] items into [RecyclerView] via [PhotoViewHolder]
 *
 */
internal class PhotosAdapter(val onPhotoClick: (Long) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val photos: MutableList<Photo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPhotoBinding = HolderPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PhotoViewHolder(holderPhotoBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Photo = photos[position]

    override fun getItemCount(): Int = photos.size

    fun addData(list: List<Photo>) {
        this.photos.clear()
        this.photos.addAll(list)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(private val binding: HolderPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(photo: Photo) {
            with(binding) {
                photoProgressBar.visibility = View.VISIBLE
                photoTextView.text = photo.title
                photoImageView.loadImage(photo.url, binding.photoProgressBar)
            }

            itemView.setOnClickListener {
                onPhotoClick(photo.id)
            }
        }
    }
}
