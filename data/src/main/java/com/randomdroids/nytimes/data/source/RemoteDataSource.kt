package com.randomdroids.nytimes.data.source

import com.randomdroids.nytimes.data.common.Response
import com.randomdroids.nytimes.domain.Article

interface RemoteDataSource {
    suspend fun getArticles(type: String, publishDate: String, socialMedia: String): Response<List<Article>>
}