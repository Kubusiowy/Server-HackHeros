package com.hackheros.domain.model


import kotlinx.serialization.Serializable

@Serializable
data class CategoryRequest(
    val name: String,
    val description: String? = null
)

@Serializable
data class CategoryResponse(
    val id: Int,
    val name: String,
    val description: String?
)

@Serializable
data class LessonRequest(
    val name: String,
    val description: String? = null
)

@Serializable
data class LessonResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val categoryId: Int
)

@Serializable
data class QuestionRequest(
    val question: String,
    val answerA: String,
    val answerB: String,
    val answerC: String? = null,
    val answerD: String? = null,
    val correctAnswer: String,
    val expGain: Int = 10
)

@Serializable
data class QuestionResponse(
    val id: Int,
    val question: String,
    val answerA: String,
    val answerB: String,
    val answerC: String?,
    val answerD: String?,
    val correctAnswer: String,
    val expGain: Int,
    val lessonId: Int
)

@Serializable
data class EducationMaterialRequest(
    val title: String? = null
)

@Serializable
data class EducationMaterialResponse(
    val id: Int,
    val title: String?,
    val lessonId: Int
)

@Serializable
data class ParagraphRequest(
    val paragraphNumber: Int,
    val header: String,
    val content: String
)

@Serializable
data class ParagraphResponse(
    val id: Int,
    val paragraphNumber: Int,
    val header: String,
    val content: String,
    val materialId: Int
)

@Serializable
data class PassedLessonRequest(
    val lessonId: Int,
    val correctAnswers: Int
)

@Serializable
data class PassedLessonResponse(
    val id: Int,
    val userId: Int,
    val lessonId: Int,
    val completedAt: String,
    val correctAnswers: Int
)
