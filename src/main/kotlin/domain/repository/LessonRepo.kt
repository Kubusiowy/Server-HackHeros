package com.hackheros.domain.repository

import com.hackheros.database.Categories
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.database.Lessons
import com.hackheros.database.PassedLessons
import com.hackheros.domain.model.LessonReponse
import com.hackheros.domain.model.PassedLessonResponse
import org.jetbrains.exposed.sql.selectAll

class LessonRepository{

    suspend fun getAllLessonsForCateogry(categoryId:Int):List<LessonReponse> = dbQuery {
        Lessons
            .selectAll().where { Lessons.categoryId eq categoryId }
            .map { row ->
                LessonReponse(
                    id = row[Lessons.id].value,
                    name=row[Lessons.name],
                    description=row[Lessons.description],
                    category_id = row[Lessons.categoryId].value
                )

            }
    }

    suspend fun getAllPassedLessons(userId: Int):List<PassedLessonResponse> = dbQuery {
        PassedLessons
            .selectAll().where { PassedLessons.userId eq userId }
            .map { row->
                PassedLessonResponse(
                    id = row[PassedLessons.id].value,
                    user_id = row[PassedLessons.userId].value,
                    lesson_id = row[PassedLessons.lessonId].value,
                    completedAt = row[PassedLessons.completedAt].toString(),
                    correctAnswer = row[PassedLessons.correctAnswers]
                )
            }
    }
}