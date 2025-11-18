package com.hackheros.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class EducationMaterialsResponse(
    val id: Int,
    val title: String?,
    val lessonId: Int,
)

@Serializable
data class ParagraphsResponse(
    val id: Int,
    val paragraph_number: Int,
    val header: String,
    val content: String,
    val material_id: Int
)