package com.randomdroids.nytimes.data.server

import com.randomdroids.nytimes.data.common.Response
import com.randomdroids.nytimes.data.server.mapper.toDomain
import com.randomdroids.nytimes.data.source.RemoteDataSource
import com.randomdroids.nytimes.domain.Article
import javax.inject.Inject

class NYTimesServerDataSource @Inject constructor(
    private val nyTimesServerService: NYTimesServerService
) :
    RemoteDataSource {

    override suspend fun getArticles(type: String, publishDate: String, socialMedia: String): Response<List<Article>> {
        return try {
            val response = nyTimesServerService.getArticles(type, publishDate, socialMedia)
            if (response.isSuccessful) {
                Response(value = response.body()?.results?.map { it.toDomain() } ?: emptyList())
            } else {
                Response(error = Exception("Request was unsuccessful"))
            }
        } catch (exception: Exception) {
            Response(error = exception.cause)
        }
    }
}