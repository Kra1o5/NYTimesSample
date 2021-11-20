package com.randomdroids.nytimes.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleImageDTO(
    @SerializedName("media-metadata") val imageMetadata: List<ImageMetadataDTO>?
) : Parcelable