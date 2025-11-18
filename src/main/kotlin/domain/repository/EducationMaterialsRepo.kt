package com.hackheros.domain.repository

import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.database.EducationMaterials
import com.hackheros.database.Paragraphs
import com.hackheros.domain.model.EducationMaterialsResponse
import com.hackheros.domain.model.ParagraphsResponse
import org.jetbrains.exposed.sql.selectAll

class EducationMaterialsRepository {

    suspend fun getAllEducationMaterialsForLesson(lessonId: Int): List<EducationMaterialsResponse> = dbQuery {
        EducationMaterials
            .selectAll().where { EducationMaterials.lessonId eq lessonId }
            .map{row ->
                EducationMaterialsResponse(
                    id = row[EducationMaterials.id].value,
                    title = row[EducationMaterials.title],
                    lessonId = row[EducationMaterials.lessonId].value,
                )
            }
    }

    suspend fun getAllParagraphsForEducationMaterial(materialId:Int):List<ParagraphsResponse> = dbQuery {
        Paragraphs
            .selectAll().where { Paragraphs.materialId eq materialId }
            .map{row->
                ParagraphsResponse(
                    id = row[Paragraphs.id].value,
                    paragraph_number = row[Paragraphs.paragraphNumber],
                    header = row[Paragraphs.header],
                    content = row[Paragraphs.content],
                    material_id = row[Paragraphs.materialId].value,
                )

            }
    }
}