package com.hackheros.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class LessonReponse(
    val id: Int,
    val name: String,
    val description: String?,
    val category_id: Int
)

@Serializable
data class LessonRequest(
    val name: String,
    val description: String?,
    val category_id: Int
)