package com.android.artgallery.presentation

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


fun ImageView.loadImageFull(url: String?) =
    Picasso.get().load(url).into(this)

fun ImageView.loadImage(url: String, progressBar: ProgressBar) =
    Picasso.get()
        .load(url)
        .placeholder(android.R.color.white)
        .into(this, object : Callback {

            override fun onError(e: java.lang.Exception?) {
                e?.printStackTrace()
            }

            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
        })