package com.randomdroids.nytimes.usecases

import com.randomdroids.nytimes.data.common.Response
import com.randomdroids.nytimes.data.common.ResultData
import com.randomdroids.nytimes.data.repository.NYTimesRepository
import com.randomdroids.nytimes.domain.Article

class GetArticleUseCase(private val nyTimesRepository: NYTimesRepository) {
    suspend fun invoke(type: String, frequency: String): ResultData<Response<List<Article>>> = nyTimesRepository.getArticles(type, frequency)
}