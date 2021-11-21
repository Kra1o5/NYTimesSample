package com.randomdroids.nytimes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.randomdroids.nytimes.R

fun ViewGroup.inflate(arrowViewItemArticle: Int, boolean: Boolean): View {
    return LayoutInflater.from(context)
        .inflate(R.layout.tablet_layout, this, false)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(this).load(url).placeholder(R.drawable.ic_baseline_image_not_supported_24)
        .override(600, 200).into(this)
}