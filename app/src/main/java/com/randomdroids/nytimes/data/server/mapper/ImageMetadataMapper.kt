package com.randomdroids.nytimes.data.server.mapper

import com.randomdroids.nytimes.data.server.dto.ImageMetadataDTO
import com.randomdroids.nytimes.domain.ImageMetadata

fun ImageMetadataDTO.toDomain() = ImageMetadata(
    this.url
)