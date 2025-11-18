package com.hackheros.domain.repository

import com.hackheros.database.Categories
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.database.Lessons
import com.hackheros.domain.model.LessonReponse
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
                    category_id = row[Categories.id].value
                )

            }

    }
}