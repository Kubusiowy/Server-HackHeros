package com.hackheros.domain.repository

import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.database.Questions
import com.hackheros.domain.model.QuestionResponse
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class QuestionRepository{

    suspend fun getQuestionForLessons(lessonId:Int) : List<QuestionResponse> = dbQuery {
        Questions
            .selectAll().where { Questions.lessonId eq lessonId }
            .map{row ->
                QuestionResponse(
                    id = row[Questions.id].value,
                    question = row[Questions.question],
                    answerA = row[Questions.answerA],
                    answerB = row[Questions.answerB],
                    answerC = row[Questions.answerC],
                    answerD = row[Questions.answerD],
                    correctAnswer = row[Questions.correctAnswer],
                    expGain = row[Questions.expGain]
                )
            }
    }

}