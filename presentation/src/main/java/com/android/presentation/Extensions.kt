package com.android.artgallery.presentation

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import coil.api.load
import coil.decode.DataSource
import coil.request.Request

fun ImageView.loadImage(url: String?) = this.load(url)

fun ImageView.loadImage(url: String, progressBar: ProgressBar) = this.load(url) {
    crossfade(true)
    listener(object : Request.Listener {
        override fun onSuccess(data: Any, source: DataSource) {
            super.onSuccess(data, source)
            progressBar.visibility = View.GONE
        }
    })
}
