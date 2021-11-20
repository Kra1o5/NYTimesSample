package com.randomdroids.nytimes.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ArticlesDTOResult(val results: List<ArticleDTO>)

@Parcelize
data class ArticleDTO(
    @SerializedName("url") val url: String?,
    @SerializedName("section") val section: String?,
    @SerializedName("byline") val author: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("publish_date") val publishDate: String?,
    @SerializedName("media") val image: List<ArticleImageDTO>?
) : Parcelable