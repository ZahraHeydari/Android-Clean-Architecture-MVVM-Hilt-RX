package com.android.artgallery.presentation.album

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.artgallery.R
import com.android.artgallery.databinding.HolderAlbumBinding
import com.android.artgallery.domain.model.Album
import com.android.artgallery.presentation.album.AlbumsAdapter.AlbumViewHolder
import java.util.*

/**
 * This class is responsible for converting each data entry [Album]
 * into [AlbumViewHolder] that can then be added to the AdapterView.
 *
 * Created by ZARA on 27/01/2019.
 */
internal class AlbumsAdapter(val mListener: OnAlbumsAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = AlbumsAdapter::class.java.name
    private val albums: MutableList<Album> = ArrayList()


    /**
     * This method is called right when adapter is created &
     * is used to initialize ViewHolders
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderAlbumBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_album, parent, false
        )
        return AlbumViewHolder(holderAlbumBinding)
    }

    /** It is called for each ViewHolder to bind it to the adapter &
     * This is where we pass data to ViewHolder
     * */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AlbumViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Album {
        return albums[position]
    }

    /**
     * This method returns the size of collection that contains the items we want to display
     * */
    override fun getItemCount(): Int {
        return albums.size
    }

    fun addData(list: List<Album>) {
        this.albums.clear()
        this.albums.addAll(list)
        notifyDataSetChanged()
    }


    inner class AlbumViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {


        fun onBind(album: Album) {
            val holderAlbumBinding = dataBinding as HolderAlbumBinding
            val albumViewModel = AlbumViewModel(album)
            holderAlbumBinding.albumViewModel = albumViewModel

            itemView.setOnClickListener {
                mListener.showPhotos(album)
            }

        }
    }

}
