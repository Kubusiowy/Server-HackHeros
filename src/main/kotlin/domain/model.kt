package com.hackheros.domain

import jdk.jfr.internal.event.EventConfiguration.timestamp
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp


object Users : IntIdTable("users") {
    val username = varchar("username", 80).uniqueIndex()
    val email = varchar("email", 75).uniqueIndex()
    val hashPass = varchar("hash_pass", 128)
    val streak = integer("streak").default(0)
    val experience = integer("experience").default(0)
    val preferredCategoryId = reference("preferred_category_id", Categories).nullable()
}

object Categories : IntIdTable("categories") {
    val name = varchar("name", 60).uniqueIndex()
    val description = varchar("description", 320).nullable()
}

object Lessons : IntIdTable("lesson") {
    val name = varchar("name", 60).uniqueIndex()
    val description = varchar("description", 320).nullable()
    val categoryId = reference("category_id", Categories)
}

object Questions : IntIdTable("questions") {
    val question = varchar("question", 220).uniqueIndex()
    val answerA = varchar("answer_a", 100)
    val answerB = varchar("answer_b", 100)
    val answerC = varchar("answer_c", 100).nullable()
    val answerD = varchar("answer_d", 100).nullable()
    val correctAnswer = varchar("correct_answer", 1)
    val expGain = integer("exp_gain").default(10)
    val lessonId = reference("lesson_id", Lessons)
}

object PassedLessons : IntIdTable("passed_lesson") {
    val userId = reference("user_id", Users)
    val lessonId = reference("lesson_id", Lessons)
    val completedAt = timestamp("completed_at")
    val correctAnswers = integer("correct_answers").default(0)
}

object EducationMaterials : IntIdTable("education_materials") {
    val title = varchar("title", 120).uniqueIndex().nullable()
    val lessonId = reference("lesson_id", Lessons)
}

object Paragraphs : IntIdTable("paragraphs") {
    val paragraphNumber = integer("paragraph_number")
    val header = varchar("header", 60)
    val content = varchar("content", 1000)
    val materialId = reference("material_id", EducationMaterials)
}