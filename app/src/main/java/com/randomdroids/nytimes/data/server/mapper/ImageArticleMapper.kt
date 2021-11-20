package com.randomdroids.nytimes.data.server.mapper

import com.randomdroids.nytimes.data.server.dto.ArticleImageDTO
import com.randomdroids.nytimes.domain.ArticleImage

fun ArticleImageDTO.toDomain() = ArticleImage(
    this.imageMetadata?.map { it -> it.toDomain() }
)