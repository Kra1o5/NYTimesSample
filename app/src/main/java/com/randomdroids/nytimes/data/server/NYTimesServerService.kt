package com.randomdroids.nytimes.data.server

import com.randomdroids.nytimes.data.server.ServerConstants.ALL_SECTIONS
import com.randomdroids.nytimes.data.server.dto.ArticleDTO
import com.randomdroids.nytimes.data.server.dto.ArticlesDTOResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NYTimesServerService {

    @GET("{type}/$ALL_SECTIONS/{frequency}?$API_KEY")
    suspend fun getArticles(@Path("type") type: String, @Path("frequency") frequency: String): Response<ArticlesDTOResult>
}