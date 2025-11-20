package com.hackheros.domain.repository

import at.favre.lib.crypto.bcrypt.BCrypt
import com.hackheros.database.Users
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.domain.model.RegisterRequest
import com.hackheros.domain.model.UpdatePreferencesRequest
import com.hackheros.domain.model.UserResponse
import org.jetbrains.exposed.sql.*



data class UserRecord(
    val id: Int,
    val username: String,
    val email: String,
    val hashPass: String,
    val streak: Int,
    val experience: Int,
    val preferredCategoryId: Int?
)


fun UserRecord.toUserResponse(): UserResponse =
    UserResponse(
        id = id,
        username = username,
        email = email,
        streak = streak,
        experience = experience,
        preferredCategoryId = preferredCategoryId
    )

class UserRepository {


    suspend fun createUser(req: RegisterRequest): UserRecord = dbQuery {
        val hash = BCrypt.withDefaults().hashToString(12, req.password.toCharArray())

        val id = Users.insertAndGetId { row ->
            row[username] = req.username
            row[email] = req.email
            row[hashPass] = hash
            row[streak] = 0
            row[experience] = 0
            row[preferredCategoryId] = null
        }.value

        UserRecord(
            id = id,
            username = req.username,
            email = req.email,
            hashPass = hash,
            streak = 0,
            experience = 0,
            preferredCategoryId = null
        )
    }


    suspend fun findByEmail(email: String): UserRecord? = dbQuery {
        Users
            .selectAll().where { Users.email eq email }
            .limit(1)
            .map { row ->
                row.toUserRecord()
            }
            .singleOrNull()
    }


    suspend fun findById(id: Int): UserRecord? = dbQuery {
        Users
            .selectAll().where{ Users.id eq id }
            .limit(1)
            .map { row ->
                row.toUserRecord()
            }
            .singleOrNull()
    }


    suspend fun updatePreferences(userId: Int, request: UpdatePreferencesRequest): UserRecord? = dbQuery {
        Users.update({ Users.id eq userId }) { row ->
            row[preferredCategoryId] = request.preferredCategoryId?.let { it }
        }

        Users
            .selectAll().where{ Users.id eq userId }
            .limit(1)
            .map { row -> row.toUserRecord() }
            .singleOrNull()
    }


    suspend fun updateExperienceAndStreak(
        userId: Int,
        expDelta: Int,
        newStreak: Int? = null
    ): UserRecord? = dbQuery {
        // Najpierw pobierz aktualnego usera
        val current = Users
            .selectAll().where { Users.id eq userId }
            .limit(1)
            .singleOrNull()
            ?: return@dbQuery null

        val currentExp = current[Users.experience]
        val currentStreak = current[Users.streak]

        val updatedExp = currentExp + expDelta
        val updatedStreak = newStreak ?: currentStreak

        Users.update({ Users.id eq userId }) { row ->
            row[experience] = updatedExp
            row[streak] = updatedStreak
        }

        Users
            .selectAll().where { Users.id eq userId }
            .limit(1)
            .map { row -> row.toUserRecord() }
            .singleOrNull()
    }


    private fun ResultRow.toUserRecord(): UserRecord =
        UserRecord(
            id = this[Users.id].value,
            username = this[Users.username],
            email = this[Users.email],
            hashPass = this[Users.hashPass],
            streak = this[Users.streak],
            experience = this[Users.experience],
            preferredCategoryId = this[Users.preferredCategoryId]?.value
        )
}
