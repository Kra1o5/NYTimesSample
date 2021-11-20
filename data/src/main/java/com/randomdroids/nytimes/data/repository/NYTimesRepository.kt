package com.randomdroids.nytimes.data.repository

import com.randomdroids.nytimes.data.common.Response
import com.randomdroids.nytimes.data.common.ResultData
import com.randomdroids.nytimes.data.source.RemoteDataSource
import com.randomdroids.nytimes.domain.Article

class NYTimesRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getArticles(type: String, frequency: String): ResultData<Response<List<Article>>> {
        return try {
            ResultData.Success(remoteDataSource.getArticles(type, frequency))
        } catch (exception: Exception) {
            ResultData.Failure(exception)
        }
    }
}