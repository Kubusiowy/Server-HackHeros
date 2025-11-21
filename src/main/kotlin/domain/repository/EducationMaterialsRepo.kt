package com.hackheros.domain.repository

import com.hackheros.database.EducationMaterials
import com.hackheros.database.Paragraphs
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.domain.model.EducationMaterialRequest
import com.hackheros.domain.model.EducationMaterialResponse
import com.hackheros.domain.model.ParagraphRequest
import com.hackheros.domain.model.ParagraphResponse
import org.jetbrains.exposed.sql.*

class EducationMaterialsRepository {

    private fun rowToMaterial(row: ResultRow) = EducationMaterialResponse(
        id = row[EducationMaterials.id].value,
        title = row[EducationMaterials.title],
        lessonId = row[EducationMaterials.lessonId].value
    )

    private fun rowToParagraph(row: ResultRow) = ParagraphResponse(
        id = row[Paragraphs.id].value,
        paragraphNumber = row[Paragraphs.paragraphNumber],
        header = row[Paragraphs.header],
        content = row[Paragraphs.content],
        materialId = row[Paragraphs.materialId].value
    )

    suspend fun getMaterialsForLesson(lessonId: Int): List<EducationMaterialResponse> = dbQuery {
        EducationMaterials
            .selectAll().where { EducationMaterials.lessonId eq lessonId }
            .map(::rowToMaterial)
    }

    suspend fun getParagraphsForMaterial(materialId: Int): List<ParagraphResponse> = dbQuery {
        Paragraphs
            .selectAll().where { Paragraphs.materialId eq materialId }
            .orderBy(Paragraphs.paragraphNumber)
            .map(::rowToParagraph)
    }

    suspend fun addMaterialForLesson(req: EducationMaterialRequest, lessonId: Int) = dbQuery {
        EducationMaterials.insert {
            it[title] = req.title
            it[EducationMaterials.lessonId] = lessonId
        }
    }

    suspend fun addParagraphForMaterial(req: ParagraphRequest, materialId: Int) = dbQuery {
        Paragraphs.insert {
            it[paragraphNumber] = req.paragraphNumber
            it[header] = req.header
            it[content] = req.content
            it[Paragraphs.materialId] = materialId
        }
    }
}
