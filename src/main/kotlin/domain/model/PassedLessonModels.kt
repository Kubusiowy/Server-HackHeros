package com.hackheros.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CompleteLessonRequest(
    val lessonId: Int,
    val correctAnswers: Int
)

@Serializable
data class PassedLessonResponse(
    val id: Int,
    val lessonId: Int,
    val correctAnswers: Int,
    val completedAt: String
)

@Serializable
data class CompletedLessonSummary(
    val lessonId: Int,
    val correctAnswers: Int,
    val completedAt: String
)

@Serializable
data class UserProgressResponse(
    val experience: Int,
    val streak: Int,
    val completedLessons: List<CompletedLessonSummary>
)
