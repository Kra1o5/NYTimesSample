package com.randomdroids.nytimes.data.server.mapper

import com.randomdroids.nytimes.data.server.dto.ArticleDTO
import com.randomdroids.nytimes.domain.Article

fun ArticleDTO.toDomain() = Article(
    this.url,
    this.section,
    this.author,
    this.title,
    this.publishDate,
    this.image?.map { it -> it.toDomain() }
)