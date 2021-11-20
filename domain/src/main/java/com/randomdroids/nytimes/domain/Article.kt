package com.randomdroids.nytimes.domain

data class Article (
    var url: String?,
    var section: String?,
    var author: String?,
    var title: String?,
    var publishDate: String?,
    var media: List<ArticleImage>?
)