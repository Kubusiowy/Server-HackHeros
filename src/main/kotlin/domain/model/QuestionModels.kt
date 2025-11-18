package com.hackheros.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponse(
    val id: Int,
    val question: String,
    val answerA: String,
    val answerB: String,
    val answerC: String?,
    val answerD: String?,
    val correctAnswer: String, // do wywalenia pozniej
    val expGain: Int
)