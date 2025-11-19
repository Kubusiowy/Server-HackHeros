package com.hackheros.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PassedLessonResponse(
    val id: Int,
    val user_id: Int,
    val lesson_id: Int,
    val completedAt:String,
    val correctAnswer: Int
)