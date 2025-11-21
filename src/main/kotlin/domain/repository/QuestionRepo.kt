package com.hackheros.domain.repository

import com.hackheros.database.Questions
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.domain.model.QuestionRequest
import com.hackheros.domain.model.QuestionResponse
import org.jetbrains.exposed.sql.*

class QuestionRepository {

    private fun rowToQuestion(row: ResultRow) = QuestionResponse(
        id = row[Questions.id].value,
        question = row[Questions.question],
        answerA = row[Questions.answerA],
        answerB = row[Questions.answerB],
        answerC = row[Questions.answerC],
        answerD = row[Questions.answerD],
        correctAnswer = row[Questions.correctAnswer],
        expGain = row[Questions.expGain],
        lessonId = row[Questions.lessonId].value
    )

    suspend fun getForLesson(lessonId: Int): List<QuestionResponse> = dbQuery {
        Questions
            .selectAll().where { Questions.lessonId eq lessonId }
            .map(::rowToQuestion)
    }

    suspend fun addForLesson(req: QuestionRequest, lessonId: Int) = dbQuery {
        Questions.insert {
            it[question] = req.question
            it[answerA] = req.answerA
            it[answerB] = req.answerB
            it[answerC] = req.answerC
            it[answerD] = req.answerD
            it[correctAnswer] = req.correctAnswer
            it[expGain] = req.expGain
            it[Questions.lessonId] = lessonId
        }
    }
}
